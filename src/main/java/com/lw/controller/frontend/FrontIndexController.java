package com.lw.controller.frontend;


import com.lw.pojo.Blog;
import com.lw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description 前端首页Controller
 */

@Controller
public class FrontIndexController {

    @Autowired
    BlogService blogService;  // blog对象的业务层

    /**
    * @Description: 将（最新，推荐） 的（前recommendBlogNum篇文章）， 显示在index上：无序分页
    * @Param: [model]
    * @return:
    */
    @RequestMapping(value = {"/", "/index"})
    public String index(Model model){

        // 查询：选推荐、最新的5篇文章
        List<Blog> blogs = blogService.findBlogListByLatestRecommendNum();

        // 传递：5篇推荐文章
        model.addAttribute("blogs", blogs);

        // 跳转：前端首页index
        return "frontend/index";
    }
}