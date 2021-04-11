package com.flagship.cloud.mall.practice.zuul.feign;

import com.flagship.cloud.mall.practice.user.model.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Author Flagship
 * @Date 2021/4/8 20:48
 * @Description
 */
@FeignClient(value = "cloud-mall-user")
public interface UserFeignClient {
    @PostMapping(value = "/checkAdminRole")
    Boolean checkAdminRole(@RequestBody User user);
}
