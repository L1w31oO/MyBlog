package com.lw.dao;

import com.lw.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description
 */
@Repository
public interface CommentMapper {

    //1、通过博客id：查询评论的总数
    Integer selectBlogCommentNumByBlogId(Long id);

    //2、通过博客id：查询评论
    List<Comment> selectCommentListByBlogId(Long id);

    //3、通过评论父id：查询所有评论
    List<Comment> selectCommentListByFatherCommentId(Long id);

    //4、通过博客id：删除对应的（所有）评论
    int deleteBlogCommentByBlogId(Long id);

    //5、插入一条评论
    int insertCommentByComment(Comment comment);

    //6、删除子评论：通过fatherCommentId
    int deleteChildCommentByFatherCommentId(Long fatherCommentId);

    //7、通过commentId：删除当前评论
    void deleteBlogCommentByCommentId(Long commentId);

    //8、查询所有博客评论的总数目
    Long selectAllBlogCommentNum();
}
