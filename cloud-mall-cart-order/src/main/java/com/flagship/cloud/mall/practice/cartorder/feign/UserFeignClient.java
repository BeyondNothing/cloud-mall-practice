package com.flagship.cloud.mall.practice.cartorder.feign;

import com.flagship.cloud.mall.practice.user.model.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Flagship
 * @Date 2021/4/9 8:30
 * @Description User的FeignClient
 */
@FeignClient(value = "cloud-mall-user")
public interface UserFeignClient {
    @GetMapping("/getUser")
    User getUser();
}
