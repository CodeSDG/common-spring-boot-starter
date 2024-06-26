package top.codesdg.common.whitelist.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author shandeguo
 * @version V1.0
 * @date 2024/6/15 下午6:46
 * @description 定义白名单注解
 * @Copyright
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoWhiteList {

    String key() default "";

    String returnValue() default "";
}
