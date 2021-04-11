package com.flagship.cloud.mall.practice.categoryproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author Flagship
 * @Date 2021/4/8 21:38
 * @Description
 */
@EnableRedisHttpSession
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.flagship.cloud.mall.practice.categoryproduct", "com.flagship.cloud.mall.practice.common.exception"})
@MapperScan(basePackages = "com.flagship.cloud.mall.practice.categoryproduct.model.dao")
public class CategoryProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(CategoryProductApplication.class, args);
    }
}
