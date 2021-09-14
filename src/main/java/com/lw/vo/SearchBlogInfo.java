package com.lw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户后端：blog搜索信息 （文章标题、分类名称、推荐状态）
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchBlogInfo {
    private String title;           //文章标题
    private String typeName;        //文章分类
    private Boolean recommend;      //文章：推荐状态
}