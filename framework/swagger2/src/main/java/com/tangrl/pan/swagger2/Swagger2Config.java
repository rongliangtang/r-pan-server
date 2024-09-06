package com.tangrl.pan.swagger2;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 接口文档配置类
 */
// 标识这是一个 Spring Boot 配置类
@SpringBootConfiguration
// 启用 Swagger 2 的自动配置，允许生成 API 文档。
@EnableSwagger2
// 启用 Swagger Bootstrap UI 的自动配置，为 Swagger UI 提供更好的用户体验。
@EnableSwaggerBootstrapUI
@Slf4j
public class Swagger2Config {

    // 注入 Swagger2ConfigProperties 配置属性
    @Autowired
    private Swagger2ConfigProperties properties;

    // Docket 是 Swagger 的主要构建器类，用于配置和初始化 Swagger 的各种选项。
    @Bean
    public Docket panServerApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .enable(properties.isShow())
                .groupName(properties.getGroupName())
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(properties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        log.info("The swagger2 have been loaded successfully!");
        return docket;
    }

    // ApiInfoBuilder 用于构建 API 文档的基本信息。
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getTermsOfServiceUrl())
                .contact(new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactName()))
                .version(properties.getVersion())
                .build();
    }

}
