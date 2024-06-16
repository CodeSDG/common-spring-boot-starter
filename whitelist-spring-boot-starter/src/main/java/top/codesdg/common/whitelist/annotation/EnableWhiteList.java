package top.codesdg.common.whitelist.annotation;

import org.springframework.context.annotation.ComponentScan;

import java.lang.annotation.*;


/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/16 下午1:50
 * @description
 * @Copyright
 */

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan(basePackages = {"top.codesdg.common.whitelist"})
public @interface EnableWhiteList {
}
