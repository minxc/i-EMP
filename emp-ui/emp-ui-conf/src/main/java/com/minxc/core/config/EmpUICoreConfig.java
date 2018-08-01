package com.minxc.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.minxc.datasource.DynamicDataSourceRegister;

/**********************************************************
 * EmpUICoreConfig
 * @author min.xianchang  xianchangmin@126.com
 * @date 2018/7/9
 *
 *********************************************************/
@Configuration
@Import(DynamicDataSourceRegister.class)
public class EmpUICoreConfig {


}
