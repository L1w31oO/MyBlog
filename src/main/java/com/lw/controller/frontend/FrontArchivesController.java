package com.lw.controller.frontend;


import com.lw.pojo.Blog;
import com.lw.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description 前端归档类Controller
 */

@Controller
public class FrontArchivesController {

    @Autowired
    private BlogService blogService;

    /**
    * @Description: 1、跳转：归档页面
    * @Param: [model, year]
    * @return:
    */
    @RequestMapping(value = {"/frontend/archives", "/frontend/archives/{year}"})
    public String archives(Model model, @PathVariable(required = false) Integer year) {

        // 查询所有博客年份
        List<Integer> years = blogService.findAllBlogYearList();

        //第一次：访问归档页面时，默认查询（最近年份）
        if(null != years && years.size() != 0 && null == year){
            year = years.get(0);
        }
        // 通过（博客年份）查询所有博客
        List<Blog> blogs = blogService.findAllBlogListByYear(year);

        model.addAttribute("years", years);     //归档的：年份
        model.addAttribute("blogs", blogs);     //该年份：归档的博客
        model.addAttribute("currentYear", year);

        return "frontend/archives";
    }

}