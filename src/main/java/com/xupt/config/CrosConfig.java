package com.xupt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题
 * @author 寇祎欢
 * @date 2020.3.29
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {
    /**
     * 实现接口后重写addCorsMappings方法
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                //将http的所有请求方式都加到可以访问的方法中
                .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTION")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
