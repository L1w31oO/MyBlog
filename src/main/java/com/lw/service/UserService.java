package com.lw.service;

import com.lw.pojo.User;
import com.lw.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description
 */
public interface UserService {
    //1、查询用户：通过username 和 password
    User findUserByUser(String username, String password);

    //2、查询：有多少个用户
    Long findUserNum();

    //3、查询：user的（前端传输对象）userVO集合
    List<UserVO> findAllUserVO();

    //4、调用更新user对象的：业务层代码
    int modifyUserByUser(User user);

    //5、添加用户：通过user对象进行添加
    int addUserByUser(User user);

    //6、删除用户：通过userId
    int removeUserByUserId(Long id);
}
