package com.pc.eliminationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EliminationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EliminationServiceApplication.class, args);
    }

}
