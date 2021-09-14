package com.lw.dao;

import com.lw.pojo.Blog;
import com.lw.vo.SearchBlogInfo;
import com.lw.vo.SwitchVO;
import org.apache.ibatis.annotations.Param;
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
public interface BlogMapper {
    // 1、查询：（最新、推荐）的num篇文章
    List<Blog> selectBlogListByLatestRecommendNum(int num);

    // 2、查询：（最新、不推荐）的num篇文章
    List<Blog> selectBlogListByLatestNotRecommendNum(int num);

    // 3、查询：通过（博客id）查询博客
    Blog selectBlogByBlogId(Long id);

    // 4、查询所有的博客
    List<Blog> selectAllBlogList();

    // 5、查询：通过（分类id）查询博客列表
    List<Blog> selectBlogListByTypeId(Long id);

    // 6、查询：博客所有的年份
    List<Integer> selectAllBlogYearList();

    // 7、查询：通过（博客年份），查询所有博客
    List<Blog> selectAllBlogListByYear(Integer year);

    // 8、查询：通过（搜索字符串），模糊查询（标题、内容、描述）包含的所有博客
    List<Blog> selectAllBlogListByQueryStr(String query);

    // 9、更新：通过（博客id），更新博客访问次数+1
    int updateBlogViewsByBlogId(Long id);

    // 10、查询：通过Search对象（title、typeName, recommend）, 查询博客信息
    List<Blog> selectBlogListBySearchBlogInfo(SearchBlogInfo searchBlogInfo);

    // 11、添加: 通过Blog对象, 并返回博客（自增ID） 注：此处须和数据库字段顺序一一对应
    Long insertBlogByBlog(Blog blog);

    // 12、更新：通过Blog对象
    int updateBlogByBlog(Blog blog);

    // 13、删除博客：通过blogId
    int deleteBlogByBlogId(Long id);

    // 14、更新博客按钮：通过(SwitchVO 按钮切换类)
    int updateBlogBySwitchVO(SwitchVO switchVO);

    // 15、查询：博客总数目
    Long selectAllBlogNum();

}
