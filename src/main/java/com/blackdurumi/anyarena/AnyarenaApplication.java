package com.blackdurumi.anyarena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AnyarenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyarenaApplication.class, args);
    }

}
