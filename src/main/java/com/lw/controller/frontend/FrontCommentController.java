package com.lw.controller.frontend;

import com.lw.pojo.Comment;
import com.lw.pojo.User;
import com.lw.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 前端：评论类（controller）
 */
@Controller
public class FrontCommentController {

    @Autowired
    CommentService commentService;

    //插入一条：评论信息
    @RequestMapping("/frontend/comments")
    public String commentAdd(Comment comment, Model model, HttpSession session){
        Object user = session.getAttribute("user");
        if(user != null){   //看是否：登录
            comment.setAvatar(((User)user).getAvatar());
            comment.setAdminComment(true);           //设置：评论权限，ture为管理员
        }

        commentService.addCommentByComment(comment);    //插入一条评论

        model.addAttribute("comments", commentService.findBlogCommentByBlogId(comment.getBlog().getId()));
        return "frontend/blog::commentList";
    }

    //查询评论信息
    //插入一条：评论信息
    @RequestMapping("/frontend/comments/{id}")
    public String commentAdd(@PathVariable Long id,Model model){

        model.addAttribute("comments", commentService.findBlogCommentByBlogId(id));
        return "frontend/blog::commentList";
    }

}