package com.lw.controller.backend;


import com.lw.pojo.User;
import com.lw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * 后台登录：
 */
@Controller
public class BackLoginController {

    @Autowired
    private UserService userService;        //用户表：业务层对象

    /**
     *
     * @param username  登录：账号
     * @param password  登录：密码
     * @param session   用来存放：错误信息  Or  用户名
     * @return
     */
    @RequestMapping(value = "/backend/login", method = RequestMethod.POST)
    public String index(@RequestParam String username,
                        @RequestParam String password,
                        Model model, HttpSession session){

        User user = userService.findUserByUser(username, password);

        System.out.println(user);

        //判断：user是否存在
        if(user == null){
            model.addAttribute("message", "用户名或密码错误!!!");
            return "frontend/login";     //跳转到：登录页面
        }

        session.setAttribute("user", user);   //将用户信息：存入user中
        return "redirect:/backend/index";
    }

}