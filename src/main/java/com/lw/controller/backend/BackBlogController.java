package com.lw.controller.backend;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lw.pojo.Type;
import com.lw.service.BlogService;
import com.lw.service.CommentService;
import com.lw.service.TypeService;
import com.lw.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description 后端博客Controller
 */
@Controller
public class BackBlogController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogService blogService;        // blog业务层对象

    @Autowired
    private TypeService typeService;        // type业务层对象


    @Autowired
    private CommentService commentService;

    //1、跳转：博客列表
    @RequestMapping("/backend/blog-list")
    public  String blogList(Model model){
        List<Type> types = typeService.findAllTypeListWithSort();
        model.addAttribute("types", types);

        return "backend/blog-list";
    }

    //2、显示：表格
    @RequestMapping("/backend/showBlogTable")
    @ResponseBody
    public DataVO<BlogVO> list(Integer page, Integer limit, SearchBlogInfo searchBlogInfo){
        DataVO<BlogVO> data = new DataVO<>();
        data.setCode(0);
        data.setMsg("");

        Page pageObj = PageHelper.startPage(page, limit);
        // 查询：所有的BlogVO
        List<BlogVO> blogVOS = blogService.findAllBlogVOListBySearchBlogInfo(searchBlogInfo);
        PageInfo<BlogVO> pageInfo = new PageInfo<>(blogVOS);

        data.setData(pageInfo.getList());
        data.setCount(pageObj.getTotal());
        return data;
    }

    //3、修改：(推荐、评论、赞赏、版权)按钮状态
    @RequestMapping("/backend/updSwitch")
    @ResponseBody
    public String t1(SwitchVO switchVO){
        // 更新博客按钮：通过(SwitchVO 按钮切换类)
        int i = blogService.modifyBlogBySwitchVO(switchVO);
        if(i > 0){
            return "true";          //修改成功
        }
        return "false";             //修改失败
    }

    //2、跳转：博客添加页面
    @RequestMapping("/backend/toAddBlogPage")
    public String toAddBlogPage(Model model){
        List<Type> types = typeService.findAllTypeListWithoutSort();
        model.addAttribute("types", types);

        logger.info(types +"");
        return "backend/blog-add";
    }

    /**
    * @Description: 3、添加博客
    * @Param: [blogInfo]
    * @return:
    */
    @PostMapping("/backend/addBlog")
    public String addBlog(BlogInfo blogInfo){
        // 通过BlogInfo对象, 添加一个Blog
        Long id = blogService.addBlogByBlog(blogInfo);

        return "redirect:/backend/index";
    }

    /**
    * @Description: 4、跳转：修改博客页面
    * @Param: [id, model]
    * @return: 博客修改页面
    */
    @RequestMapping("/backend/toUpdBlogPage/{id}")
    public String toUpdBlogPage(@PathVariable Long id, Model model){

        // 通过分类id拿到标签信息，并放在model里返回给博客修改页面
        model.addAttribute("types", typeService.findAllTypeListWithoutSort());

        // 通过博客id拿到标签信息，并放在model里返回给博客修改页面
        model.addAttribute("blog", blogService.findBlogByBlogId(id));

        return "backend/blog-upd";
    }

    //5、正式修改：博客
    @RequestMapping("/backend/updBlog")
    public String updBlog(BlogInfo blogInfo){
        blogService.modifyBlogByBlog(blogInfo);                  //更新blog 通过BlogInfo对象
        return "redirect:/backend/index";
    }

    //6、批量：选中博客 （单篇、多篇皆可）
    @RequestMapping("/backend/delAllBlog")
    @ResponseBody
    public String delAllBlog(Long[] ids){
        for(Long id:ids){
            commentService.removeBlogCommentByBlogId(id);         //删除：评论
            blogService.removeBlogByBlogId(id);            //删除：blog
        }
        return "true";
    }
}