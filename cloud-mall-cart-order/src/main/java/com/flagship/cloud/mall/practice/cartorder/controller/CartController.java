package com.flagship.cloud.mall.practice.cartorder.controller;

import com.flagship.cloud.mall.practice.cartorder.feign.UserFeignClient;
import com.flagship.cloud.mall.practice.cartorder.model.vo.CartVO;
import com.flagship.cloud.mall.practice.cartorder.service.CartService;
import com.flagship.cloud.mall.practice.common.common.ApiRestResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Flagship
 * @Date 2021/3/27 23:10
 * @Description 购物车控制器
 */
@RestController
public class CartController {
    @Resource
    CartService cartService;

    @Resource
    UserFeignClient userFeignClient;

    /**
     * 添加商品进购物车
     * @param productId 商品id
     * @param count 数量
     * @return 统一返回对象
     */
    @ApiOperation("添加购物车")
    @PostMapping("/cart")
    public ApiRestResponse add(@RequestParam Integer productId, @RequestParam Integer count) {
        Integer userId = userFeignClient.getUser().getId();
        List<CartVO> cartVOList = cartService.add(userId, productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    /**
     * 获取当前用户购物车列表
     * @return 统一返回对象
     */
    @ApiOperation("购物车列表")
    @GetMapping("/carts")
    public ApiRestResponse list() {
        Integer userId = userFeignClient.getUser().getId();
        List<CartVO> cartVOList = cartService.list(userId);
        return ApiRestResponse.success(cartVOList);
    }

    /**
     * 更新购物车
     * @return 统一返回对象
     */
    @ApiOperation("更新购物车")
    @PutMapping("/cart")
    public ApiRestResponse update(@RequestParam Integer productId, @RequestParam Integer count) {
        Integer userId = userFeignClient.getUser().getId();
        List<CartVO> cartVOList = cartService.update(userId, productId, count);
        return ApiRestResponse.success(cartVOList);
    }

    /**
     * 删除购物车
     * @return 统一返回对象
     */
    @ApiOperation("删除购物车")
    @DeleteMapping("/cart")
    public ApiRestResponse delete(@RequestParam Integer productId) {
        Integer userId = userFeignClient.getUser().getId();
        List<CartVO> cartVOList = cartService.delete(userId, productId);
        return ApiRestResponse.success(cartVOList);
    }

    /**
     * 选中/不选中购物车
     * @return 统一返回对象
     */
    @ApiOperation("选中/不选中购物车")
    @PutMapping("/cart/select")
    public ApiRestResponse select(@RequestParam Integer cartId, @RequestParam Integer selected) {
        Integer userId = userFeignClient.getUser().getId();
        List<CartVO> cartVOList = cartService.selectOrNot(userId, cartId, selected);
        return ApiRestResponse.success(cartVOList);
    }

    /**
     * 全选中/全不选中购物车
     * @return 统一返回对象
     */
    @ApiOperation("全选中/全不选中购物车")
    @PutMapping("/cart/selectAll")
    public ApiRestResponse selectAll(@RequestParam Integer selected) {
        Integer userId = userFeignClient.getUser().getId();
        List<CartVO> cartVOList = cartService.selectOrNot(userId, null, selected);
        return ApiRestResponse.success(cartVOList);
    }
}
