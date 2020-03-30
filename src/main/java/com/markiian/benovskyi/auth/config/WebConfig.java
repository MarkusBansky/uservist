package com.markiian.benovskyi.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Value("${uservist.cors.origins}")
    private String allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/auth/**");
        registry
                .addMapping("/api/**")
                .allowedOrigins(allowedOrigins.split(","))
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*");;
    }
}
