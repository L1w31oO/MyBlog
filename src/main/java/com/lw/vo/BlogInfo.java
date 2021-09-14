package com.lw.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于：添加博客、更新博客（存放blog信息）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogInfo {
    private Long bid;
    private String title;
    private String content;
    private String flag;
    private Long typeId;
    private String firstPicture;
    private String description;
    private String published;          //发布状态
    private String recommend;          //推荐
    private String commentable;       //评论
    private String appreciation;       //赞赏
    private String shareStatement;     //版权
    private Long uid;
}