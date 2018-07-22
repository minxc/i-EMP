package com.minxc.datasource.annotation;

import java.lang.annotation.*;

/**
 * @ClassName TargetDataSource
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/22 19:02
 * @Version 1.0
 **/

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name() default "defaultDataSource";
}
