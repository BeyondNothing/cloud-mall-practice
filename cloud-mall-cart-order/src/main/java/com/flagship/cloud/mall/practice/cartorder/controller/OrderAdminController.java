package com.flagship.cloud.mall.practice.cartorder.controller;

import com.flagship.cloud.mall.practice.cartorder.service.OrderService;
import com.flagship.cloud.mall.practice.common.common.ApiRestResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Flagship
 * @Date 2021/3/28 19:58
 * @Description
 */
@RestController
@RequestMapping("/admin")
public class OrderAdminController {
    @Resource
    OrderService orderService;
    /**
     * 获取后台订单列表
     * @param pageNum 页码
     * @param pageSize 每页的数量
     * @return 统一返回对象
     */
    @ApiOperation("后台订单列表")
    @GetMapping("/orders")
    public ApiRestResponse listForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo pageInfo = orderService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    /**
     * 获取后台订单列表
     * @param orderNo 订单号
     * @return 统一返回对象
     */
    @ApiOperation("管理员发货")
    @PutMapping("/order/deliver")
    public ApiRestResponse deliver(@RequestParam String orderNo) {
        orderService.deliver(orderNo);
        return ApiRestResponse.success();
    }
}
