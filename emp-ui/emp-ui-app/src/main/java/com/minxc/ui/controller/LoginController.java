package com.minxc.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName LoginController
 * @Description 用户登录界面
 * @Author Xianchang.min
 * @Date 2018/7/24 0:32
 * @Version 1.0
 **/

@Controller
public class LoginController {

//    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

}
