package com.flagship.cloud.mall.practice.cartorder.feign;

import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Flagship
 * @Date 2021/4/9 8:20
 * @Description 商品的FeignClient
 */
@FeignClient(value = "cloud-mall-category-product")
public interface ProductFeignClient {
    @GetMapping("/product/forFeign")
    Product detailForFeign(@RequestParam Integer id);

    @PutMapping("/product/updateStock")
    void updateStock(@RequestParam Integer productId, @RequestParam Integer stock);
}
