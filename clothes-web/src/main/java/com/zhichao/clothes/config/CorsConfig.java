package com.zhichao.clothes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 配置允许跨域的路径
                registry.addMapping("/**") // 所有接口
                        .allowedOriginPatterns("*") // 允许所有域名访问
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许的请求方法
                        .allowedHeaders("*") // 允许的请求头
                        .allowCredentials(true) // 是否允许携带凭证（如 cookies）
                        .maxAge(3600); // 预检请求的缓存时间（单位：秒）
            }
        };
    }
}