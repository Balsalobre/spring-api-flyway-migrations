package com.cbo;

import com.cbo.account.management.shared.domain.Service;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@MapperScan("com.cbo.layered.infrastructure.mybatis")
@ComponentScan(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Service.class
        ),
        basePackages = {"com.cbo.account.management" }
)
public class UserAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAccountServiceApplication.class, args);
    }

}
