package com.zhichao.clothes.custom.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(
                new Info()
                        .title("换衣小程序API")
                        .version("1.0")
                        .description("换衣小程序API"));
    }

    @Bean
    public GroupedOpenApi loginAPI() {

        return GroupedOpenApi.builder().group("用户登录管理").
                pathsToMatch(
                        "/app/login/**",
                        "/app/info"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi systemAPI() {

        return GroupedOpenApi.builder().group("系统信息管理").
                pathsToMatch(
                        "/app/system/**"
                ).
                build();
    }



    @Bean
    public GroupedOpenApi apartmentAPI() {

        return GroupedOpenApi.builder().group("换衣信息管理").
                pathsToMatch(
                        "/app/file/**"
                ).build();
    }
    @Bean
    public GroupedOpenApi leaseAPI() {
        return GroupedOpenApi.builder().group("详细身体数据管理").
                pathsToMatch(
                        "/app/getMeasurement/**",
                        "/app/setMeasurement/**",
                        "/app/updateUserInfo/**"
                ).build();
    }
    @Bean
    public GroupedOpenApi userAPI() {
        return GroupedOpenApi.builder().group("平台用户管理").
                pathsToMatch(
                        "/app/user/**"
                ).build();
    }
}
