package com.interest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author a1002
 */
@SpringBootApplication
@MapperScan("com.interest.mapper")
public class InterestApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterestApplication.class, args);
    }

}
