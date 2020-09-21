package com.markiian.benovskyi.auth;

import it.ozimov.springboot.mail.configuration.EnableEmailTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEmailTools
public class UvApplication {
    public static void main(String[] args) {
        SpringApplication.run(UvApplication.class, args);
    }
}
