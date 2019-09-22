package com.goodcub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.goodcub.vci.mapper")
@SpringBootApplication
public class EcWebprojectApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EcWebprojectApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(EcWebprojectApplication.class, args);
    }

}
