package com.lw.controller.frontend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontAboutController {
    //关于我页面
    @RequestMapping("/frontend/about")
    public String about(){
        return "frontend/about";
    }
}