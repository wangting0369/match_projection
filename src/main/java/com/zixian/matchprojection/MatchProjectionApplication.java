package com.zixian.matchprojection;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zixian.matchprojection.mapper")
public class MatchProjectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchProjectionApplication.class, args);
    }

}
