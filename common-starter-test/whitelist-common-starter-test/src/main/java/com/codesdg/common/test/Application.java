package com.codesdg.common.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import top.codesdg.common.whitelist.annotation.EnableWhiteList;

import java.lang.annotation.Documented;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午11:37
 * @description
 * @Copyright
 */

@EnableWhiteList
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
