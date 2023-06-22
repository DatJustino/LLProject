package com.example.llproject.config;

import com.example.llproject.service.AdminPasswordService;
import com.example.llproject.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private AdminPasswordService adminPasswordService;
/*  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf(); // your other configurations
  }*/

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();

    // Configure your CORS settings here. This is just an example.
    configuration.setAllowedOrigins(Arrays.asList("*"));
    configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
    /*configuration.setAllowCredentials(true);*/
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }

  @Bean
  public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("user")
        .password(passwordEncoder.encode("userPass"))
        .roles("USER")
        .build());
    manager.createUser(User.withUsername("admin")
        .password(passwordEncoder.encode("adminPass"))
        .roles("USER", "ADMIN")
        .build());
    return manager;
  }


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().and().csrf()
        .disable();

        /*.authorizeRequests()
        .requestMatchers(HttpMethod.DELETE, "/admin/**", "/user/**", "/login/**")
        .hasRole("ADMIN")
        .requestMatchers("/admin/**")
        .hasAnyRole("ADMIN")
        .requestMatchers("/user/**")
        .hasAnyRole("USER", "ADMIN")
        .requestMatchers("/login/**")
        .anonymous()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/

    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.debug(true);
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) throws Exception {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService(passwordEncoder));
    provider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(Arrays.asList(provider));
  }
  }

