package com.flagship.cloud.mall.practice.categoryproduct.controller;

import com.flagship.cloud.mall.practice.categoryproduct.common.ProductConstant;
import com.flagship.cloud.mall.practice.categoryproduct.model.pojo.Product;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.AddProductReq;
import com.flagship.cloud.mall.practice.categoryproduct.model.request.UpdateProductReq;
import com.flagship.cloud.mall.practice.categoryproduct.service.ProductService;
import com.flagship.cloud.mall.practice.common.common.ApiRestResponse;
import com.flagship.cloud.mall.practice.common.exception.FlagshipMallException;
import com.flagship.cloud.mall.practice.common.exception.FlagshipMallExceptionEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * @Author Flagship
 * @Date 2021/3/26 8:01
 * @Description
 */
@RestController
public class ProductAdminController {
    @Resource
    private ProductService productService;

    @Value("${file.upload.ip}")
    String ip;

    @Value("${file.upload.port}")
    Integer port;

    /**
     * 增加商品
     * @param addProductReq 增加商品对象
     * @return 统一响应对象
     */
    @ApiOperation("新增商品")
    @PostMapping("/admin/product")
    public ApiRestResponse addProduct(@Valid @RequestBody AddProductReq addProductReq) {
        productService.add(addProductReq);
        return ApiRestResponse.success();
    }

    /**
     * 上传文件接口
     * @param httpServletRequest http请求对象
     * @param file 上传文件
     * @return 统一响应对象
     */
    @ApiOperation("上传文件")
    @PostMapping("/admin/upload")
    public ApiRestResponse upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        File fileDirectory = new File(ProductConstant.FILE_UPLOAD_DIR);
        File destFile = new File(ProductConstant.FILE_UPLOAD_DIR + newFileName);
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new FlagshipMallException(FlagshipMallExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
            return ApiRestResponse.success(getHost(new URI(httpServletRequest.getRequestURL().toString())) + "/category-product/images/" + newFileName);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return ApiRestResponse.error(FlagshipMallExceptionEnum.UPLOAD_FAILED);
        }
    }

    /**
     * 把uir多余的部分剔除，构造出想要的uri
     * @param uri 原始uri
     * @return 目标uri
     */
    private URI getHost(URI uri) {
        URI effectiveUri = null;
        try {
            effectiveUri = new URI(uri.getScheme(), uri.getUserInfo(), ip, port, null, null, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return effectiveUri;
    }

    /**
     * 更新商品
     * @param updateProductReq 更新商品对象
     * @return 统一响应对象
     */
    @ApiOperation("更新商品")
    @PutMapping("/admin/product")
    public ApiRestResponse updateProduct(@Valid @RequestBody UpdateProductReq updateProductReq) {
        productService.update(updateProductReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("删除商品")
    @DeleteMapping(value = "/admin/product/{id:\\d+}")
    public ApiRestResponse deleteProduct(@PathVariable(value = "id") Integer id) {
        productService.delete(id);
        return ApiRestResponse.success();
    }

    @ApiOperation("批量上下架接口")
    @PutMapping("/admin/product/status")
    public ApiRestResponse batchUpdateSellStatus(@RequestParam Integer[] ids, @RequestParam Integer status) {
        productService.batchUpdateSellStatus(ids, status);
        return ApiRestResponse.success();
    }

    @ApiOperation("后台商品列表接口")
    @GetMapping("/admin/products")
    public ApiRestResponse list(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        PageInfo<Product> pageInfo = productService.listForAdmin(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }
}
