package com.aozhou.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.aozhou.code.domain.mapper")
public class aozhouCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(aozhouCodeApplication.class, args);
    }

}
