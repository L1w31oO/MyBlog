package com.lw.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 用于查询：blog信息显示所用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogVO {
    private Long id;
    private String title;
    private String typeName;
    private Date createTime;
    private Boolean published;
    private Boolean recommend;
    private Boolean commentable;
    private Boolean appreciation;
    private Boolean shareStatement;
}