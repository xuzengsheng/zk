package com.nccs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-14 14:38
 * @description: swagger全局配置
 **/

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.nccs.controller"))//定义api扫描包的路径
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("SpringBoot整合Swagger")  //API标题
                        .description("SpringBoot整合Swagger，详细信息......") //描述
                        .version("9.0")  //版本
                        .contact(new Contact("啊啊啊啊", "blog.csdn.net", "aaa@gmail.com"))
                        .license("The Apache License") //
                        .licenseUrl("http://www.baidu.com") //自定义url
                        .build());
    }
}
