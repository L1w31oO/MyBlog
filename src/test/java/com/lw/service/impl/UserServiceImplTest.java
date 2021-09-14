package com.lw.service.impl;

import com.lw.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Date 2021/8/10 19:05
 * @Version 1.0
 * @Description
 */
public class UserServiceImplTest {

    @Test
    public void insUserByUser(User user) {
        String newPassword = "123456789";
        String salt = "2020";
        user.setPassword(newPassword+salt);
        System.out.println();
    }
}