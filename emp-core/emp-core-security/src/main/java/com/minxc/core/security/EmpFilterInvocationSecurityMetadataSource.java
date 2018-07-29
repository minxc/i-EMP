package com.minxc.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Collection;
import java.util.Map;

/**
 * @ClassName EmpFilterInvocationSecurityMetadataSource
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/29 13:03
 * @Version 1.0
 **/


@Slf4j
public class EmpFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        return null;
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }


    @Override
    public boolean supports(Class<?> clazz) {

        return FilterInvocation.class.isAssignableFrom(clazz);
//        return true;   //可作为默认实现
    }





}
