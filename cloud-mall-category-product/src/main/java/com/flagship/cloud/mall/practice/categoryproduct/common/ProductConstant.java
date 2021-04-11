package com.flagship.cloud.mall.practice.categoryproduct.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Flagship
 * @Date 2021/4/8 22:49
 * @Description
 */
@Component
public class ProductConstant {
    public static String FILE_UPLOAD_DIR;

    @Value("${file.upload.dir}")
    public void setFileUploadDir(String fileUploadDir) {
        FILE_UPLOAD_DIR = fileUploadDir;
    }
}
