package com.jason.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jason.springboot.dao")
public class SpringbootPaquApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPaquApplication.class, args);
    }
}
