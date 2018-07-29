package com.minxc.core.config;

import com.minxc.core.security.CSRFSecurityRequestMatcher;
import com.minxc.core.security.EmpAccessDecisionManager;
import com.minxc.core.security.EmpFilterInvocationSecurityMetadataSource;
import com.minxc.core.security.EmpFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName EmpWebSecurityConfig
 * @Description EMP系统安全框架配置核心类
 * @Author Xianchang.min
 * @Date 2018/7/29 1:20
 * @Version 1.0
 **/

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class EmpWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
//        auth.authenticationProvider() ;

    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        //remember me
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {//setting是自定义的配置参数
        http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())　　//设定一个自定义的的登陆页面URL
                .and().authorizeRequests()
                .antMatchers("/images/**", "/checkcode", "/scripts/**", "/styles/**").permitAll()　　//完全允许访问的一些URL配置
                .antMatchers(settings.getPermitall().split(",")).permitAll()
                .anyRequest().authenticated()
                .and().csrf().requireCsrfProtectionMatcher(csrfSecurityRequestMatcher())　　//跨站请求伪造，这是一个防止跨站请求伪造攻击的策略配置
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().logout().logoutSuccessUrl(settings.getLogoutsuccssurl())　　//设定登出成功的链接
                .and().exceptionHandling().accessDeniedPage(settings.getDeniedpage())　　　//配置拒绝访问的提示链接
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(tokenRepository());  //用来记住用户的登录状态，用户没执行推出下次打开页面不用登陆，时效自己设置
    }


    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
        jtr.setDataSource(dataSource);
        return jtr;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){//设置登陆成功处理器
        return new LoginSuccessHandler();
    }

    @Bean
    public FilterSecurityInterceptor customFilter() throws Exception{

        FilterSecurityInterceptor  filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.set
        EmpFilterSecurityInterceptor customFilter = new EmpFilterSecurityInterceptor();
        customFilter.setSecurityMetadataSource(securityMetadataSource());
        customFilter.setAccessDecisionManager(accessDecisionManager());
        customFilter.setAuthenticationManager(authenticationManager);
        return customFilter;
    }

    @Bean
    public EmpAccessDecisionManager accessDecisionManager() {
        return new EmpAccessDecisionManager();
    }

    @Bean
    public EmpFilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource(settings.getUrlroles());
    }


    private CSRFSecurityRequestMatcher csrfSecurityRequestMatcher(){ //加入需要排除阻止CSRF攻击的链表链接，链接地址中包含/rest字符串的，对其忽略CSRF保护策略
        CSRFSecurityRequestMatcher csrfSecurityRequestMatcher = new CSRFSecurityRequestMatcher();
        List<String> list = new ArrayList<String>();
        list.add("/rest/");
        csrfSecurityRequestMatcher.setExecludedUrls(list);
        return csrfSecurityRequestMatcher;
    }
}
