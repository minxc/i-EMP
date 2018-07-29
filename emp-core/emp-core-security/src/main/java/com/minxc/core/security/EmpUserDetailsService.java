package com.minxc.core.security;

import com.minxc.core.entity.User;
import com.minxc.core.entity.UserRoleLink;
import com.minxc.core.repository.UserRepository;
import com.minxc.core.security.utils.EmpSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

/**
 * @ClassName EmpUserDetailService
 * @Description 自定义UserDetailsService ,从数据库中抓取用户信息
 * @Author Xianchang.min
 * @Date 2018/7/29 0:39
 * @Version 1.0
 **/
@Service("userDetailsService")
@Transactional
public class EmpUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loginUser = userRepository.findByUserName(username);
        String userName = loginUser.getUserName();
        String password = loginUser.getPassword();
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(userName, password, EmpSecurityUtils.getAuthorities(loginUser));
        return user;
    }


}
