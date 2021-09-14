package com.lw.controller.backend;


import com.lw.service.BlogService;
import com.lw.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description 后端首页Controller
 */

@Controller
public class BackIndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;

    //跳转：后台首页
    @RequestMapping("/backend/index")
    public String afterIndex(){
        return "backend/index";
    }

    //welcome
    @RequestMapping("/backend/welcome")
    public String welcome(Model model){
        Long blogsNum = blogService.findAllBlogNum();        //博客总数目
        Long commentsNum = commentService.findAllBlogCommentNum();   //评论总数目

        model.addAttribute("blogsNum", blogsNum);
        model.addAttribute("commentsNum", commentsNum);
        return "backend/welcome";
    }
}