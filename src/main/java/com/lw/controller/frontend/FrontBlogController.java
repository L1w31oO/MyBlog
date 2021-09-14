package com.lw.controller.frontend;

import com.lw.pojo.Blog;
import com.lw.pojo.Comment;
import com.lw.service.BlogService;
import com.lw.service.CommentService;
import com.lw.util.MarkdownUtils;
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
 * @Description 前端博客Controller
 */
@Controller
public class FrontBlogController {

    @Autowired
    BlogService blogService;  // blog业务层对象

    @Autowired
    CommentService commentService;  // comment业务层对象


    /**
    * @Description: 跳转：博客页面
    * @Param: [id, model]
    * @return:
    */
    @RequestMapping("/frontend/blog/{id}")
    public String blog(@PathVariable Long id, Model model){

        //1、通过ID获取到：博客
        Blog blog = blogService.findBlogByBlogId(id);

        //2、将markdown转为：html
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));

        //3、给博客：设置评论
        List<Comment> comments = commentService.findBlogCommentByBlogId(id);

        //4、博客：访问次数加1
        blogService.modifyBlogViewsByBlogId(id);

        model.addAttribute("blog", blog);
        model.addAttribute("comments", comments);
        return "frontend/blog";
    }
}