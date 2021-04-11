package com.flagship.cloud.mall.practice.cartorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Flagship
 * @Date 2021/4/9 8:02
 * @Description 购物车订单启动类
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@ComponentScan(basePackages = {"com.flagship.cloud.mall.practice.cartorder", "com.flagship.cloud.mall.practice.common.exception"})
@MapperScan(basePackages = "com.flagship.cloud.mall.practice.cartorder.model.dao")
@EnableFeignClients
public class CartOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartOrderApplication.class, args);
    }
}
