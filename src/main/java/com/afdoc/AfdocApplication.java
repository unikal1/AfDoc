package com.afdoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AfdocApplication {

    public static void main(String[] args) {
        SpringApplication.run(AfdocApplication.class, args);
    }

}
