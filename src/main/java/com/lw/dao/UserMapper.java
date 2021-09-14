package com.lw.dao;

import com.lw.pojo.User;
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
public interface UserMapper {
    //1、查询用户：通过用户对象（账号、密码）
    User selectUserByUser(User user);

    //2、查询：用户的数目
    Long selectUserNum();

    //3、查询：user集合， 不包含blogs字段
    List<User> selectAllUserList();

    //4、查询：通过userId查询user对象
    User selectUserByUserId(Long id);

    //5、更新：通过user (更新用户信息)
    int updateUserByUser(User user);

    //6、添加用户：通过user对象进行添加
    int insertUserByUser(User user);

    //7、删除用户：通过userId
    int deleteUserByUserId(Long id);
}
