package com.flagship.cloud.mall.practice.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Flagship
 * @Date 2021/4/8 17:15
 * @Description 用户服务启动类
 */
@EnableRedisHttpSession
@EnableSwagger2
@SpringBootApplication()
@ComponentScan(basePackages = {"com.flagship.cloud.mall.practice.user", "com.flagship.cloud.mall.practice.common.exception"})
@MapperScan(basePackages = "com.flagship.cloud.mall.practice.user.model.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
