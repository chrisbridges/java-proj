package com.example.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**") // Apply to all endpoints
        .allowedOrigins("*") // Allow all origins
        .allowedMethods("*") // Allow all HTTP methods
        .allowedHeaders("*"); // Allow all headers
  }
}
// override CORS