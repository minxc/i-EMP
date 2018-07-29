package com.minxc.core.security.utils;

import com.google.common.collect.Sets;
import com.minxc.core.entity.Role;
import com.minxc.core.entity.User;
import com.minxc.core.entity.UserRoleLink;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;

/**
 * @ClassName EmpSecurityUtils
 * @Description TODO
 * @Author Xianchang.min
 * @Date 2018/7/29 14:11
 * @Version 1.0
 **/

public class EmpSecurityUtils {

    /*
     * @method getAuthorities
     * @Description //获取登录用户的所有权限信息
     * @Author Xianchang.min
     * @Date 14:12 2018/7/29
     * @Param [loginUser] 
     * @return java.util.Collection<org.springframework.security.core.GrantedAuthority>
     **/
    public static Collection<GrantedAuthority> getAuthorities(User loginUser){
        Set<GrantedAuthority> authoritySet = Sets.newHashSet();
        Set<UserRoleLink> links = loginUser.getUserRoleLinks();
        for(UserRoleLink lnk : links){
            Role role = lnk.getRole();
            SimpleGrantedAuthority  simpleGrantedAuthority = new SimpleGrantedAuthority(role.getCode());
            authoritySet.add(simpleGrantedAuthority);
        }
        return  authoritySet;
    }

}
