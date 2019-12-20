package com.github.luoyemyy.mall.common.config

import com.github.luoyemyy.mall.util.Const
import com.google.common.collect.Lists
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ApiKey
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class Swagger {
    @Bean
    fun adminApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("管理后台文档")
                .apiInfo(apiInfo1())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.luoyemyy.mall.controller.admin"))
                .paths(PathSelectors.any())
                .build().securitySchemes(token())
    }

    private fun apiInfo1(): ApiInfo {
        return ApiInfoBuilder().title("管理后台文档").version("1.0").build()
    }

    @Bean
    fun appletApi(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .groupName("小程序文档")
                .apiInfo(apiInfo2())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.luoyemyy.mall.controller.applet"))
                .paths(PathSelectors.any())
                .build().securitySchemes(token())
    }

    private fun token(): List<ApiKey>? {
        return Lists.newArrayList(ApiKey("登录凭证", Const.TOKEN_KEY, "header"))
    }

    private fun apiInfo2(): ApiInfo {
        return ApiInfoBuilder().title("小程序文档").version("1.0").build()
    }
}