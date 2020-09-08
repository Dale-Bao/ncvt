package com.ncvt.website.web;

import com.dtflys.forest.annotation.ForestScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xueneng on 2020/9/3.
 *         Description:
 */
@SpringBootApplication
@MapperScan("com.ncvt.*.service.mapper")
@ComponentScan({
        "com.ncvt.*.web",
        "com.ncvt.*.web.*",
})
@ForestScan("com.ncvt.common.client")
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
public class WebsiteApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class);
    }

}
