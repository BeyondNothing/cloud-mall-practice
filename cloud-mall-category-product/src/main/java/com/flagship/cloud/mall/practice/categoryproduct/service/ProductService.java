package com.flagship.cloud.mall.practice.categoryproduct.service;


import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Product;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.AddProductReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.ProductListReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.UpdateProductReq;
import com.github.pagehelper.PageInfo;

/**
 * @Author Flagship
 * @Date 2021/3/25 9:05
 * @Description 商品service
 */
public interface ProductService {
    /**
     * 新增商品
     * @param addProductReq 新增数据对象
     */
    void add(AddProductReq addProductReq);

    /**
     * 更新商品
     * @param updateProductReq 新增商品对象
     */
    void update(UpdateProductReq updateProductReq);

    /**
     * 删除商品
     * @param id 商品id
     */
    void delete(Integer id);

    /**
     * 状态上下架
     * @param ids 商品id列表
     * @param status 商品状态
     */
    void batchUpdateSellStatus(Integer[] ids, Integer status);

    /**
     * 查询商品列表（管理员）
     * @param pageNum 页号
     * @param pageSize 每页的行数
     * @return 商品列表
     */
    PageInfo<Product> listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 获取商品细节
     * @param id 商品id
     * @return 商品对象
     */
    Product detail(Integer id);

    /**
     * 查询商品列表（用户）
     * @param productListReq 商品列表请求对象
     * @return 商品列表
     */
    PageInfo<Product> list(ProductListReq productListReq);

    /**
     * 更新库存
     * @param productId 商品id
     * @param stock 更新后的库存
     */
    void updateStock(Integer productId, Integer stock);
}
