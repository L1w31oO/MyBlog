package com.lw.controller.backend;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lw.pojo.User;
import com.lw.service.UserService;
import com.lw.vo.DataVO;
import com.lw.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 用户：业务层对象
 */
@Controller
public class BackUserController {

    @Autowired
    private UserService userService;

    //1-1、跳转：用户列表
    @RequestMapping("/backend/userList")
    public String userList(){
        return "backend/user-list";
    }

    //1-2、异步返回：用户（用户列表）数据
    @RequestMapping("/backend/queryAllUser")
    @ResponseBody
    public DataVO<UserVO>  queryAllUser(Integer page, Integer limit){
        DataVO<UserVO> dataVO = new DataVO<>();
        //为dataVO设置：值
        dataVO.setCode(0);
        dataVO.setMsg("");
        dataVO.setCount(userService.findUserNum());

        PageHelper.startPage(page, limit);
        List<UserVO> userVOS = userService.findAllUserVO();
        PageInfo<UserVO> pageInfo = new PageInfo<>(userVOS);

        dataVO.setData(pageInfo.getList());

        return dataVO;
    }

    //2-1：跳转：修改用户页面
    @RequestMapping("/backend/userUpdForm")
    public String userForm(){
        return "backend/user-updForm";
    }

    //2-2：、正式修改（分类名称）
    @RequestMapping("/backend/updUser")
    @ResponseBody
    public String updUser(Long userId, String newNickname,
                          String newAvatar, String newEmail,
                          String newPassword){


        User user = new User(userId, newNickname, newPassword, newEmail, newAvatar);
        int state = userService.modifyUserByUser(user); //调用更新user对象的：业务层代码

        if(state > 0){      //状态 > 0 时： 表示更新成功，否则失败
            return "true";
        }
        return "false";
    }

    //3-1、跳转：用户添加页面
    @RequestMapping("/backend/userAddPage")
    public String userAddPage(){
        return "backend/user-add";
    }

    //3-2、正式添加：用户界面
    @RequestMapping(value = "/backend/userAdd", method = RequestMethod.POST)
    public String userAdd(User user, Model model){

        int state = userService.addUserByUser(user);    //添加用户

        if(state > 0){
            model.addAttribute("userAddMessage", "true"); //用户添加成功
        }else{
            model.addAttribute("userAddMessage", "false");//用户添加失败
        }

        System.out.println(state);

        return "backend/user-add";
    }

    //4、删除：用户
    @RequestMapping("/backend/delUser")
    @ResponseBody
    public String delUser(Long id){

        int state = userService.removeUserByUserId(id);

        if(state > 0)
            return "true";

        return "false";
    }
}