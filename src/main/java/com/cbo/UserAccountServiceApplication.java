package com.cbo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cbo.layered.infrastructure.mybatis")
public class UserAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAccountServiceApplication.class, args);
    }

}
