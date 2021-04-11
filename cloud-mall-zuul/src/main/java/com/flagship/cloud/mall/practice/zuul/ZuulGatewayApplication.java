package com.flagship.cloud.mall.practice.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @Author Flagship
 * @Date 2021/4/8 20:54
 * @Description
 */
@EnableRedisHttpSession
@EnableZuulProxy
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.flagship.cloud.mall.practice.zuul", "com.flagship.cloud.mall.practice.common.exception"})
public class ZuulGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatewayApplication.class, args);
    }
}
