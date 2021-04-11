package com.flagship.cloud.mall.practice.categoryproduct.controller;

import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Category;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.AddCategoryReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.UpdateCategoryReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.vo.CategoryVO;
import com.flagship.cloud.mall.practice.categoryproduct.service.CategoryService;
import com.flagship.cloud.mall.practice.common.common.ApiRestResponse;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author Flagship
 * @Date 2021/3/25 8:50
 * @Description
 */
@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    /**
     * 后台新增一个分类
     * @param addCategoryReq 传入参数
     * @param session session对象
     * @return 统一响应对象
     */
    @ApiOperation("后台新增分类")
    @PostMapping("/admin/category")
    public ApiRestResponse addCategory(@Valid @RequestBody AddCategoryReq addCategoryReq, HttpSession session) {
        categoryService.add(addCategoryReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台更新分类")
    @PutMapping("/admin/category")
    public ApiRestResponse updateCategory(@Valid @RequestBody UpdateCategoryReq updateCategoryReq, HttpSession session) {
        Category category = new Category();
        BeanUtils.copyProperties(updateCategoryReq, category);
        categoryService.update(category);
        return ApiRestResponse.success();
    }

    /**
     * 删除分类
     * @param id 分类id
     * @return 统一响应对象
     */
    @ApiOperation("后台删除分类")
    @DeleteMapping(value = "/admin/category/{id:\\d+}")
    public ApiRestResponse deleteCategory(@PathVariable("id") Integer id) {
        categoryService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台分类列表")
    @GetMapping("/admin/categories")
    public ApiRestResponse listCategoryForAdmin(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<Category> pageInfo = categoryService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("用户分类列表")
    @GetMapping("/categories")
    public ApiRestResponse listCategoryForCustomer() {
        List<CategoryVO> categoryVOList = categoryService.listCategoryForCustomer(0);
        return ApiRestResponse.success(categoryVOList);
    }
}
