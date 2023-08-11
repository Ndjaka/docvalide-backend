package com.ghosttech.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Replace "/api/v1/**" with your API path
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://test.docvalide.com",
                        "https://docvalide.com",
                        "https://app.docvalide.com",
                        "https://admin.docvalide.com"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

}
