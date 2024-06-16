package com.codesdg.common.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午11:37
 * @description
 * @Copyright
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.codesdg.common.test", "top.codesdg.common.whitelist"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
