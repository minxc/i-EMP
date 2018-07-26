package com.minxc.core.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName HomeController
 * @Description 系统UI的入口
 * @Author Xianchang.min
 * @Date 2018/7/26 12:47
 * @Version 1.0
 **/
@Slf4j
@Controller
public class HomeController {

    /*
     * @method homeBoard
     * @Description 返回系统主页面
     * @Author Xianchang.min
     * @Date 19:16 2018/7/26
     * @Param [request, response]
     * @return org.springframework.web.servlet.ModelAndView
     **/
    @RequestMapping("/home")
    public ModelAndView homeBoard(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView("home/index");
        return mav;
    }





}
