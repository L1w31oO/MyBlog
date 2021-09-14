package com.lw.service.impl;

import com.lw.dao.CommentMapper;
import com.lw.pojo.Comment;
import com.lw.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    //1、通过博客id：查询评论
    @Override
    public List<Comment> findBlogCommentByBlogId(Long id) {
        List<Comment> temp = commentMapper.selectCommentListByBlogId(id); //通过blogId查询到：所有评论
        List<Comment> comments = new LinkedList<>();            //用于：存放分级评论（共2级）


        //1.1：comments中：存放1级评论 （注：1级评论就是，没有父类的评论）
        for(Comment comment : temp) {
            if (comment.getParentComment() == null || comment.getParentComment().getId() == -1) {
                comments.add(comment);
            }
        }

        //1.2：将1级评论（各自都子类，都归类2级评论）
        for (Comment sonComment: comments){
            List<Comment> twoComm = new ArrayList<>(); //用于存放：各个1级评论的（2级评论，包含所有子代）

            recursion(twoComm, sonComment);            //递归：查询当前sonComment子代，全放入2级评论
            sonComment.setReplyComments(twoComm);      //将2级评论：设置为1级评论的子代
        }

        return comments;          //返回评论
    }

    //递归：查询当前sonComment子代，全放入2级评论
    private void recursion(List<Comment> twoComm, Comment sonComment){
        if(sonComment != null){     //首先判断：子代是否为null

            //不为null则：查询到它的下一代
            List<Comment> comments = commentMapper.selectCommentListByFatherCommentId(sonComment.getId());

            //遍历：下一代
            for(Comment comment:comments){
                //为子类：设置父类（名称）, 其父类也就是sonComment
                comment.getParentComment().setNickname(sonComment.getNickname());

                //将当前：子节点，放入2级评论
                twoComm.add(comment);

                //对该子节点：进一步递归判断
                recursion(twoComm, comment);
            }
        }
    }

    //2、通过博客id：删除对应的（所有）评论
    @Override
    public int removeBlogCommentByBlogId(Long id) {
        return commentMapper.deleteBlogCommentByBlogId(id);
    }


    //3、插入一条评论
    @Override
    public int addCommentByComment(Comment comment) {
        comment.setCreateTime(new Date());          //设置：评论时间

        if(null == comment.getAvatar() || "".equals(comment.getAvatar()))
            comment.setAvatar("/images/comment.jpg");   //设置：评论头像

        if(null == comment.getParentComment()){         //设置：父元素
            comment.setParentComment(new Comment());
        }

        System.out.println(comment);
        return commentMapper.insertCommentByComment(comment);
    }

    //4-1、删除评论：通过comment
    @Override
    public void removeBlogCommentByComment(Comment comment) {
        recursionDelete(comment);                                   //删除：根
        commentMapper.deleteBlogCommentByCommentId(comment.getId());   //递归删除：后代
    }

    //4-2、递归删除：评论
    public void recursionDelete(Comment comment){
        System.out.println("判断评论：" + comment.getId());
        List<Comment> comments = commentMapper.selectCommentListByFatherCommentId(comment.getId());

        for(Comment son : comments){
            if(null != commentMapper.selectCommentListByFatherCommentId(son.getId())){
                removeBlogCommentByComment(son);
            }
            commentMapper.deleteBlogCommentByCommentId(son.getId());
        }
    }

    //5、评论总数目
    @Override
    public Long findAllBlogCommentNum() {
        return commentMapper.selectAllBlogCommentNum();
    }
}