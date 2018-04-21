package com.oukingtim.config;



import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.MultipartConfigElement;


/**
 * Created by oukingtim
 */
@Slf4j
@Aspect
@Configuration
public class WebConfiguration  {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/sys/**", config);
        source.registerCorsConfiguration("/druid/**", config);
        source.registerCorsConfiguration("/management/**", config);
        return new CorsFilter(source);
    }


    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大
        factory.setMaxFileSize(1024 * 1024 * 1000);
        // 设置总上传数据总大小
        factory.setMaxRequestSize(1024 * 1024 * 1000 * 10);
        return factory.createMultipartConfig();
    }
}
