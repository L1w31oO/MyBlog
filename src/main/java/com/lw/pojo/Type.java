package com.lw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Date 2021/8/10 14:15
 * @Version 1.0
 * @Description
 */
@Data                   // get、set、toString
@NoArgsConstructor      // 无参构造
@AllArgsConstructor     // 有参构造
public class Type {
    private Long id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();    // 一个类型（对应）多篇博客
}
