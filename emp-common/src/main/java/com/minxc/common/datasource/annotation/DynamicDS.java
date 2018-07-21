package com.minxc.common.datasource.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(
        RetentionPolicy.RUNTIME
)
@Target(value = ElementType.METHOD)
public @interface DynamicDS {

    String name() default "defaultDS";  //默认数据源的名称defaultDS
}
