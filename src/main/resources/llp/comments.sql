create table comments
(
    commentid    int auto_increment
        primary key,
    content      varchar(255) not null,
    creationtime datetime(6)  not null,
    blogpostid   int          not null,
    constraint FKi6vemkqlywf9faepuwjka2lj9
        foreign key (blogpostid) references blogposts (blogpostid)
);

INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (1, 'Comment 1 for Blog Post 1', '2023-05-06 18:28:14.276120', 1);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (2, 'Comment 2 for Blog Post 1', '2023-05-06 18:28:14.276120', 1);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (3, 'Comment 1 for Blog Post 2', '2023-05-06 18:28:14.276120', 2);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (4, 'Comment 1 for Blog Post 1', '2023-05-06 18:28:57.108109', 3);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (5, 'Comment 2 for Blog Post 1', '2023-05-06 18:28:57.108109', 3);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (6, 'Comment 1 for Blog Post 2', '2023-05-06 18:28:57.108109', 4);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (7, 'Comment 1 for Blog Post 1', '2023-05-07 00:09:36.429140', 5);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (8, 'Comment 2 for Blog Post 1', '2023-05-07 00:09:36.429140', 5);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (9, 'Comment 1 for Blog Post 2', '2023-05-07 00:09:36.429140', 6);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (10, 'Comment 1 for Blog Post 1', '2023-05-07 00:12:40.312874', 7);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (11, 'Comment 2 for Blog Post 1', '2023-05-07 00:12:40.312874', 7);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (12, 'Comment 1 for Blog Post 2', '2023-05-07 00:12:40.312874', 8);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (13, 'Comment 1 for Blog Post 1', '2023-05-07 00:13:15.410858', 9);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (14, 'Comment 2 for Blog Post 1', '2023-05-07 00:13:15.410858', 9);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (15, 'Comment 1 for Blog Post 2', '2023-05-07 00:13:15.410858', 10);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (16, 'Comment 1 for Blog Post 1', '2023-05-07 00:17:34.663439', 11);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (17, 'Comment 2 for Blog Post 1', '2023-05-07 00:17:34.663439', 11);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (18, 'Comment 1 for Blog Post 2', '2023-05-07 00:17:34.663439', 12);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (19, 'Comment 1 for Blog Post 1', '2023-05-07 00:20:23.164589', 13);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (20, 'Comment 2 for Blog Post 1', '2023-05-07 00:20:23.164589', 13);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (21, 'Comment 1 for Blog Post 2', '2023-05-07 00:20:23.164589', 14);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (22, 'Comment 1 for Blog Post 1', '2023-05-07 00:28:47.543990', 15);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (23, 'Comment 2 for Blog Post 1', '2023-05-07 00:28:47.543990', 15);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (24, 'Comment 1 for Blog Post 2', '2023-05-07 00:28:47.543990', 16);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (25, 'Comment 1 for Blog Post 1', '2023-05-07 00:53:10.726092', 17);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (26, 'Comment 2 for Blog Post 1', '2023-05-07 00:53:10.726092', 17);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (27, 'Comment 1 for Blog Post 2', '2023-05-07 00:53:10.726092', 18);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (28, 'Comment 1 for Blog Post 1', '2023-05-07 00:55:22.790378', 19);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (29, 'Comment 2 for Blog Post 1', '2023-05-07 00:55:22.790378', 19);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (30, 'Comment 1 for Blog Post 2', '2023-05-07 00:55:22.790378', 20);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (31, 'Comment 1 for Blog Post 1', '2023-05-07 01:06:01.578862', 21);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (32, 'Comment 2 for Blog Post 1', '2023-05-07 01:06:01.578862', 21);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (33, 'Comment 1 for Blog Post 2', '2023-05-07 01:06:01.578862', 22);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (34, 'Comment 1 for Blog Post 1', '2023-05-07 01:07:05.330205', 23);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (35, 'Comment 2 for Blog Post 1', '2023-05-07 01:07:05.330205', 23);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (36, 'Comment 1 for Blog Post 2', '2023-05-07 01:07:05.330205', 24);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (37, 'Comment 1 for Blog Post 1', '2023-05-07 01:07:51.515678', 25);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (38, 'Comment 2 for Blog Post 1', '2023-05-07 01:07:51.516675', 25);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (39, 'Comment 1 for Blog Post 2', '2023-05-07 01:07:51.516675', 26);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (40, 'Comment 1 for Blog Post 1', '2023-05-07 12:18:09.192408', 27);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (41, 'Comment 2 for Blog Post 1', '2023-05-07 12:18:09.192408', 27);
INSERT INTO llp.comments (commentid, content, creationtime, blogpostid) VALUES (42, 'Comment 1 for Blog Post 2', '2023-05-07 12:18:09.192408', 28);
