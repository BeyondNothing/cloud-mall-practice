package com.flagship.cloud.mall.practice.categoryproduct.controller;

import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Product;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.ProductListReq;
import com.flagship.cloud.mall.practice.categoryproduct.service.ProductService;
import com.flagship.cloud.mall.practice.common.common.ApiRestResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author Flagship
 * @Date 2021/3/26 21:54
 * @Description
 */
@RestController
public class ProductController {
    @Resource
    private ProductService productService;

    @ApiOperation("商品详情")
    @GetMapping("/product/{id:\\d+}")
    public ApiRestResponse detail(@PathVariable("id") Integer id) {
        Product product = productService.detail(id);
        return ApiRestResponse.success(product);
    }

    @ApiOperation("前台商品列表")
    @GetMapping("/products")
    public ApiRestResponse list(ProductListReq productListReq) {
        PageInfo<Product> list = productService.list(productListReq);
        return ApiRestResponse.success(list);
    }

    @ApiOperation("商品详情（内部调用）")
    @GetMapping("/product/forFeign")
    public Product detailForFeign(@RequestParam Integer id) {
        return productService.detail(id);
    }

    @ApiOperation("更新库存（内部调用）")
    @PutMapping("/product/updateStock")
    public void updateStock(@RequestParam Integer productId, @RequestParam Integer stock) {
        productService.updateStock(productId, stock);
    }
}
