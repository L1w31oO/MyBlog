package com.lw.service;

import com.lw.pojo.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description
 */
public interface CommentService {
    //1、通过博客id：查询评论
    List<Comment> findBlogCommentByBlogId(Long id);

    //2、通过博客id：删除对应的（所有）评论
    int removeBlogCommentByBlogId(Long id);

    //3、插入一条评论
    int addCommentByComment(Comment comment);

    //4、删除评论：通过comment
    void removeBlogCommentByComment(Comment comment);

    //5、评论总数目
    Long findAllBlogCommentNum();
}
