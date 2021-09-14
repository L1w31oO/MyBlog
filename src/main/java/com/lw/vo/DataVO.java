package com.lw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用于后台：Layui数据表格（异步传输：显示数据）
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVO<T> {
    private Integer code;
    private String msg;
    private Long count;
    private List<T> data;

}
