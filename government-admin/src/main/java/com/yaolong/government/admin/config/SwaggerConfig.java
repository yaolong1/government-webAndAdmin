package com.yaolong.government.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yaoLong
 * @date 2019-12-03  12:22
 */
@EnableSwagger2
@ComponentScan(basePackages = {"com.yaolong.government.admin.controller"})
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("政府网页模板")//标题
                .description("description:项目摘要")//描述
                .termsOfServiceUrl("http://www.google.com.hk")//（不可见）条款地址，公司内部使用的话不需要配
                .contact(new Contact("Devil", "https://yaolong.top", "1638538651@qq.com"))//作者信息
                .version("0.0.1")//版本号
                .build();
    }
}