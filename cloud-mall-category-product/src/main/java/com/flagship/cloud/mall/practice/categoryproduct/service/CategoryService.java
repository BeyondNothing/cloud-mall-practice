package com.flagship.cloud.mall.practice.categoryproduct.service;

import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Category;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.AddCategoryReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.vo.CategoryVO;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author Flagship
 * @Date 2021/3/25 9:05
 * @Description 分类service
 */
public interface CategoryService {
    /**
     * 添加分类
     * @param addCategoryReq 分类请求对象
     */
    void add(AddCategoryReq addCategoryReq);

    /**
     * 更新分类
     * @param updateCategory 要更新的分类对象
     */
    void update(Category updateCategory);

    /**
     * 删除分类
     * @param id 分类id
     */
    void delete(Integer id);

    /**
     * 查询分类列表（管理员）
     * @param pageNum 页码
     * @param pageSize 每页的数量
     * @return 分页对象
     */
    PageInfo<Category> listForAdmin(Integer pageNum, Integer pageSize);

    /**
     * 查询某id分类下的所有子分类（管理员）
     *
     * @return CategoryVO列表
     * @param parentId 父id
     */
    List<CategoryVO> listCategoryForCustomer(Integer parentId);
}
