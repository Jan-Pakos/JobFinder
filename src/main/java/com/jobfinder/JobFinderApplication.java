package com.jobfinder;

import com.jobfinder.infrastructure.security.jwt.JwtConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JwtConfigurationProperties.class})
public class JobFinderApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobFinderApplication.class, args);
    }
}
