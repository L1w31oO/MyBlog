package com.lw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Date 2021/8/10 14:16
 * @Version 1.0
 * @Description
 */
@Data                   //get、set、toString
@NoArgsConstructor      //无参构造
@AllArgsConstructor     //有参构造
public class User {
    private Long id;            //用户ID

    private String username;    //用户名
    private String password;    //密码
    private String type;        //类型
    private String nickname;    //昵称
    private String avatar;      //头像
    private String email;       //邮箱
    private Date createTime;    //创建时间
    private Date updateTime;    //更新时间
    private List<Blog> blogs = new ArrayList<>();


    public User(Long id, String nickname, String password, String email, String avatar) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
    }
}
