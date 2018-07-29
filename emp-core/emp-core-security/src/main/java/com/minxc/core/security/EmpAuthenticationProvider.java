package com.minxc.core.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName EmpAuthenticationProvider
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/29 0:44
 * @Version 1.0
 **/

public class EmpAuthenticationProvider implements AuthenticationProvider {



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {

        return true;
    }
}
