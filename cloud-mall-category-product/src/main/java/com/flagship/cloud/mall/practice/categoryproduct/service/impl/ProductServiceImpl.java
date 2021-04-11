package com.flagship.cloud.mall.practice.categoryproduct.service.impl;

import com.flagship.cloud.mall.practice.categoryproduct.model.dao.ProductMapper;
import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Product;
import com.flagship.cloud.mall.practice.categoryproduct.model.query.ProductListQuery;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.AddProductReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.ProductListReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.UpdateProductReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.vo.CategoryVO;
import com.flagship.cloud.mall.practice.categoryproduct.service.CategoryService;
import com.flagship.cloud.mall.practice.categoryproduct.service.ProductService;
import com.flagship.cloud.mall.practice.common.common.Constant;
import com.flagship.cloud.mall.practice.common.exception.FlagshipMallException;
import com.flagship.cloud.mall.practice.common.exception.FlagshipMallExceptionEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Flagship
 * @Date 2021/3/26 8:09
 * @Description 商品service实现类
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Resource
    private CategoryService categoryService;

    @Override
    public void add(AddProductReq addProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(addProductReq, product);
        Product productOld = productMapper.selectByName(product.getName());
        if (productOld != null) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.insertSelective(product);
        if (count == 0) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.CREATE_FAILED);
        }
    }

    @Override
    public void update(UpdateProductReq updateProductReq) {
        Product product = new Product();
        BeanUtils.copyProperties(updateProductReq, product);
        Product oldProduct = productMapper.selectByName(product.getName());
        if (oldProduct != null && !oldProduct.getId().equals(product.getId())) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.NAME_EXISTED);
        }
        int count = productMapper.updateByPrimaryKeySelective(product);
        if (count == 0) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public void delete(Integer id) {
        int count = productMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.DELETE_FAILED);
        }
    }

    @Override
    public void batchUpdateSellStatus(Integer[] ids, Integer status) {
        int count = productMapper.batchUpdateSellStatus(ids, status);
        if (count == 0) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public PageInfo<Product> listForAdmin(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> products = productMapper.selectListForAdmin();
        return new PageInfo<>(products);
    }

    @Override
    public Product detail(Integer id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Product> list(ProductListReq productListReq) {
        //构建Query对象
        ProductListQuery productListQuery = new ProductListQuery();

        //搜索处理
        if (!StringUtils.isEmpty(productListReq.getKeyword())) {
            String keyword = new StringBuilder().append("%").append(productListReq.getKeyword()).append("%").toString();
            productListQuery.setKeyword(keyword);
        }

        //目录处理：递归查询当前分类包含子分类下的商品
        if (productListReq.getCategoryId() != null) {
            List<CategoryVO> categoryVoS = categoryService.listCategoryForCustomer(productListReq.getCategoryId());
            ArrayList<Integer> categoryIds = new ArrayList<>();
            categoryIds.add(productListReq.getCategoryId());
            getCategoryIds(categoryVoS, categoryIds);
            productListQuery.setCategoryIds(categoryIds);
        }

        //排序处理
        String orderBy = productListReq.getOrderBy();
        if (Constant.ProductListOrderBy.PRICE_ASC_DESC.contains(orderBy)) {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize(), orderBy);
        } else {
            PageHelper.startPage(productListReq.getPageNum(), productListReq.getPageSize());
        }

        List<Product> productList = productMapper.selectList(productListQuery);
        return new PageInfo<>(productList);
    }

    private void getCategoryIds(List<CategoryVO> categoryVOList, ArrayList<Integer> categoryIds) {
        for (CategoryVO categoryVO : categoryVOList) {
            if (categoryVO != null) {
                categoryIds.add(categoryVO.getId());
                getCategoryIds(categoryVO.getChildCategory(), categoryIds);
            }
        }
    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        Product product = new Product();
        product.setId(productId);
        product.setStock(stock);
        int count = productMapper.updateByPrimaryKey(product);
        if (count ==0 ) {
            throw new FlagshipMallException(FlagshipMallExceptionEnum.UPDATE_FAILED);
        }
    }
}
