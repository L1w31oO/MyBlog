package com.lw.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 用于后台：按钮状态切换类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwitchVO {
    private Long blogId;            //blogId
    private String switchName;      //按钮名称
    private Boolean switchState;    //切换：按钮状态
}