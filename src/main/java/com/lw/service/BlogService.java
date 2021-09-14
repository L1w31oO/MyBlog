package com.lw.service;

import com.lw.pojo.Blog;
import com.lw.vo.BlogInfo;
import com.lw.vo.BlogVO;
import com.lw.vo.SearchBlogInfo;
import com.lw.vo.SwitchVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description
 */
public interface BlogService {

    //1、查询：最新推荐的文章，默认5篇（可更改） 若推荐文章不足5篇， 则选取：最新文章 （补齐5篇）
    List<Blog> findBlogListByLatestRecommendNum();

    //2、查询：通过（博客id） 查询到（博客信息）
    Blog findBlogByBlogId(Long id);

    //3、查询：通过（分类id）查询到（博客列表）
    List<Blog> findBlogListByTypeId(Long id);

    //4、查询：博客所有的年份
    List<Integer> findAllBlogYearList();

    //5、查询：通过（博客年份），查询所有博客
    List<Blog> findAllBlogListByYear(Integer year);

    //6、查询：通过（搜索字符串），模糊查询（标题、内容、描述）包含的所有博客
    List<Blog> findBlogListByQueryStr(String query);

    //7、更新：通过（博客id）， 更新博客（访问次数+1）
    int modifyBlogViewsByBlogId(Long id);

    //8、查询：通过Search对象（title、typeName, recommend）, 查询博客信息
    List<Blog> findBlogListBySearchBlogInfo(SearchBlogInfo searchBlogInfo);

    //9、添加博客: 通过BlogInfo对象, 添加一个Blog
    Long addBlogByBlog(BlogInfo blogInfo);

    //10、更新博客：通过BlogInfo对象
    int modifyBlogByBlog(BlogInfo blogInfo);

    //11、删除博客：通过blogId
    int removeBlogByBlogId(Long id);

    //12、查询：所有博客信息
    List<Blog> findAllBlogList();

    //13、查询：所有的BlogVO
    List<BlogVO> findAllBlogVOListBySearchBlogInfo(SearchBlogInfo searchBlogInfo);

    //14、更新博客按钮：通过(SwitchVO 按钮切换类)
    int modifyBlogBySwitchVO(SwitchVO switchVO);

    //15、查询：博客总数目
    Long findAllBlogNum();
}
