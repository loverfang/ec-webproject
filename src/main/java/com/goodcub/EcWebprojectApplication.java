package com.goodcub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;
import java.io.File;

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

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory multipartConfigFactory = new MultipartConfigFactory();
        // 开发Windows环境
        String location = System.getProperty("user.dir") + "/data/tmp";

        // 发布环境
        // String location = System.getProperty("user.dir") + "/home/gelanding1gdeqlia4nddbi4nbg/data/tmp";
        File file = new File(location);
        if(!file.exists()){
            file.mkdirs();
        }
        multipartConfigFactory.setLocation(location);
        return multipartConfigFactory.createMultipartConfig();
    }
}
