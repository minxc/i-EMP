package com.minxc.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Description 用户登录界面
 * @Author Xianchang.min
 * @Date 2018/7/24 0:32
 * @Version 1.0
 **/

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("name", "min");
        map.addAttribute("bookTitle", "min");
        // return模板文件的名称，对应src/main/resources/templates/welcome.html
        return "login";
    }

}
