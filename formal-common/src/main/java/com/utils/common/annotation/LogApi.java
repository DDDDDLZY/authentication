package com.utils.common.annotation;

import java.lang.annotation.*;

/**
 * @Author: lzy
 * @Date: 2021/11/25 11:07
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogApi {
    String value() default "";

    String type() default "";
}
