package com.flagship.cloud.mall.practice.cartorder.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author Flagship
 * @Date 2021/3/25 15:18
 * @Description 配置地址映射
 */
@Configuration
public class FlagshipMallWebMvcConfig implements WebMvcConfigurer {

    @Value("${file.upload.dir}")
    String FILE_UPLOAD_DIR;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/admin/");
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + FILE_UPLOAD_DIR);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
