package com.lw.controller.frontend;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lw.service.BlogService;
import com.lw.pojo.Blog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FrontSearchController {
    @Autowired
    private BlogService blogService;

    @Value("${searchSize}")
    private Integer pageSizeConfig;         //搜索页面：按多少篇文章分页
    private Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping("/frontend/search")
    public String search(
            @RequestParam(required = false,defaultValue = "") String query,
            @RequestParam(required = false,defaultValue = "1")int pagenum,
            @RequestParam(required = false,defaultValue = "2")int pagesize,
            Model model){

        if(pagesize == 2){
            pagesize = pageSizeConfig;
        }

        PageHelper.startPage(pagenum, pagesize);

        // 通过（搜索字符串），模糊查询（标题、内容、描述）包含的所有博客
        List<Blog> blogs =  blogService.findBlogListByQueryStr(query);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("currentQuery", query);
        model.addAttribute("pageInfo", pageInfo);

        return "frontend/search";
    }
}