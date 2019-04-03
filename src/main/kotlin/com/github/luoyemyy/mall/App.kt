package com.github.luoyemyy.mall

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = ["com.github.luoyemyy.mall.core.mapper", "com.github.luoyemyy.mall.core.dao"])
class App

fun main(args: Array<String>) {
    runApplication<App>(*args)
}
