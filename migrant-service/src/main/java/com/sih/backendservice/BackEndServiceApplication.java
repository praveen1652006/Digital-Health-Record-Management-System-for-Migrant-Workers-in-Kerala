package com.sih.backendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableElasticsearchRepositories
public class BackEndServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndServiceApplication.class, args);
    }

}
