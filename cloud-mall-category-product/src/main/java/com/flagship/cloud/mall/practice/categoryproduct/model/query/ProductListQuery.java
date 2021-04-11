package com.flagship.cloud.mall.practice.categoryproduct.model.query;

import java.util.List;

/**
 * @Author Flagship
 * @Date 2021/3/26 22:05
 * @Description 查询商品列表的Query
 */
public class ProductListQuery {
    private String keyword;

    private List<Integer> categoryIds;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }
}
