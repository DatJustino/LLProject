  package com.example.llproject.controller;

  import com.example.llproject.model.BlogPost;
  import com.example.llproject.model.Comment;
  import com.example.llproject.service.BlogService;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.http.ResponseEntity;
  import org.springframework.web.bind.annotation.*;

  import java.util.List;
  import java.util.Optional;

  @RestController
  @RestControllerAdvice
  @RequestMapping("/blogposts")
  @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}, allowedHeaders = {"Content-Type", "Authorization"})
  public class BlogPostController {

    private final BlogService blogService;

    @Autowired
    public BlogPostController(BlogService blogService) {
      this.blogService = blogService;
    }

    @PostMapping("/create")
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
      BlogPost newBlogPost = blogService.createBlogPost(blogPost);
      return ResponseEntity.status(HttpStatus.CREATED).body(newBlogPost);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogPost>> getAllBlogPosts() {
      List<BlogPost> blogPosts = blogService.getAllBlogPosts();
      return ResponseEntity.ok(blogPosts);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Integer id) {
      Optional<BlogPost> blogPostOptional = blogService.getBlogPostById(id);
      return blogPostOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Void> updateBlogPost(@PathVariable Integer id, @RequestBody BlogPost updatedBlogPost) {
      blogService.updateBlogPost(id, updatedBlogPost);
      return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Integer id) {
      blogService.deleteBlogPost(id);
      return ResponseEntity.noContent().build();
    }

    @PostMapping("/{blogPostId}/comments")
    public ResponseEntity<Void> addCommentToBlogPost(
        @PathVariable Integer blogPostId,
        @RequestBody Comment comment) {
      blogService.addCommentToBlogPost(blogPostId, comment);
      return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{blogPostId}/comments/{commentId}")
    public ResponseEntity<Void> deleteCommentFromBlogPost(
        @PathVariable Integer blogPostId,
        @PathVariable Integer commentId) {
      Optional<BlogPost> blogPostOptional = blogService.getBlogPostById(blogPostId);
      if (blogPostOptional.isPresent()) {
        BlogPost blogPost = blogPostOptional.get();
        Comment comment = blogPost.getComments().stream()
            .filter(c -> c.getCommentId().equals(commentId))
            .findFirst()
            .orElse(null);
        if (comment != null) {
          blogPost.removeComment(comment);
          blogService.updateBlogPost(blogPostId, blogPost); // Update the blog post after removing the comment
          return ResponseEntity.noContent().build();
        }
      }
      return ResponseEntity.notFound().build();
    }
  }
