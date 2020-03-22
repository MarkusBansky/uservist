package com.markiian.benovskyi.auth;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEmailTools
public class Application {

    @Value("{uservist.cors.origins}")
    private String allowedOrigins;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/api/*")
                        .allowedOrigins(allowedOrigins.split(","))
                        .allowedHeaders("*");
            }
        };
    }
}
