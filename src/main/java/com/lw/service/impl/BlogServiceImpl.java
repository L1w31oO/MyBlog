package com.lw.service.impl;

import com.lw.dao.BlogMapper;
import com.lw.dao.CommentMapper;
import com.lw.dao.TypeMapper;
import com.lw.pojo.Blog;
import com.lw.pojo.Type;
import com.lw.pojo.User;
import com.lw.service.BlogService;
import com.lw.vo.BlogInfo;
import com.lw.vo.BlogVO;
import com.lw.vo.SearchBlogInfo;
import com.lw.vo.SwitchVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description BlogService实现类
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogMapper blogMapper;              // 博客：持久层

    @Autowired
    CommentMapper commentMapper;        // 评论：持久层

    @Autowired
    TypeMapper typeMapper;              // 分类：持久层

    @Value("${recommendBlogNumSize}")
    private Integer recommendBlogNum;   //设置首页：最新推荐的（博客数目）

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
    * @Description: 1、查询：最新，推荐的（recommendBlogNum）篇博客 （默认为：5篇）
    *                       当不足（recommendBlogNum）篇， 则选取：最新，不推荐的博客 （补齐recommendBlogNum篇）
    * @Param: []
    * @return: 选择的recommendBlogNum篇博客
    */
    @Override
    public List<Blog> findBlogListByLatestRecommendNum() {

        // 查询recommendBlogNum篇最新推荐的博客
        List<Blog> blogs = blogMapper.selectBlogListByLatestRecommendNum(recommendBlogNum);

        // 日志打印查询的（最新，推荐）博客信息
        for (Blog b : blogs){
            logger.info("最新，推荐博客 ===>>>" + b.getTitle() + "===>>>" +  b.getUser());
        }

        // 当（最新、推荐文章）不足recommendBlogNum篇
        if(blogs.size() < recommendBlogNum){

            // 查询少于recommendBlogNum数量的（最新、非推荐）博客
            List<Blog> temp = blogMapper.selectBlogListByLatestNotRecommendNum(recommendBlogNum - blogs.size());

            // 日志打印查询的博客信息
            for(Blog b1 : temp){
                logger.info("最新，非推荐博客 ===>>>" + b1.getTitle() + "===>>>" + b1.getUser());
            }

            // （最新、非推荐）的文章补齐
            blogs.addAll(temp);
        }

        // 为博客：设置总评论次数
        for(Blog blog : blogs) {
            blog.setCommentNum(commentMapper.selectBlogCommentNumByBlogId(blog.getId()));
        }
        return blogs;
    }

    /**
    * @Description: 2、查询：通过（博客id）查询到（博客信息）
    * @Param: [id]
    * @return:
    */
    @Override
    public Blog findBlogByBlogId(Long id) {
        return blogMapper.selectBlogByBlogId(id);
    }

    /**
    * @Description: 3、查询：通过（分类id）查询到（博客列表）
    * @Param: [id]
    * @return: 博客列表
    */
    @Override
    public List<Blog> findBlogListByTypeId(Long id){

        // 根据分类id查询到的博客列表
        List<Blog> blogs = blogMapper.selectBlogListByTypeId(id);

        // 为博客：设置总评论次数
        for (Blog blog : blogs) {
            blog.setCommentNum(commentMapper.selectBlogCommentNumByBlogId(blog.getId()));
        }
        return blogs;
    }

    /**
    * @Description: 4、查询：博客所有的年份
    * @Param: []
    * @return:
    */
    @Override
    public List<Integer> findAllBlogYearList() {
        return blogMapper.selectAllBlogYearList();
    }

    /**
    * @Description: 5、查询：通过（博客年份）， 查询所有博客
    * @Param: [year]
    * @return:
    */
    @Override
    public List<Blog> findAllBlogListByYear(Integer year) {
        return blogMapper.selectAllBlogListByYear(year);
    }

    /**
    * @Description: 6、查询：通过（搜索字符串），模糊查询（标题、内容、描述）包含的所有博客
    * @Param: [query]
    * @return:
    */
    @Override
    public List<Blog> findBlogListByQueryStr(String query) {

        // 通过（搜索字符串），模糊查询（标题、内容、描述）包含的所有博客
        List<Blog> blogs = blogMapper.selectAllBlogListByQueryStr(query);

        //为博客：设置总评论次数
        for(Blog blog : blogs) {
            blog.setCommentNum(commentMapper.selectBlogCommentNumByBlogId(blog.getId()));
        }
        return blogs;
    }

    /**
    * @Description: 7、更新：通过（博客id）， 更新博客（访问次数+1）
    * @Param: [id]
    * @return:
    */
    @Override
    public int modifyBlogViewsByBlogId(Long id) {
        return blogMapper.updateBlogViewsByBlogId(id);
    }

    /**
    * @Description: 8、查询：通过Search对象（title、typeName, recommend）, 查询博客信息
    * @Param: [searchInfo]
    * @return:
    */
    @Override
    public List<Blog> findBlogListBySearchBlogInfo(SearchBlogInfo searchBlogInfo) {
        return blogMapper.selectBlogListBySearchBlogInfo(searchBlogInfo);
    }

    /**
    * @Description: 9、添加: 通过BlogInfo对象, 添加一个Blog, 返回博客（自增ID）
    * @Param: [blogInfo]
    * @return:
    */
    @Override
    public Long addBlogByBlog(BlogInfo blogInfo) {

        Blog blog = new Blog(blogInfo.getTitle(), blogInfo.getContent(), blogInfo.getFirstPicture(), blogInfo.getFlag(), blogInfo.getDescription());

        // 日志打印
        logger.info("打印一下：发布信息 ===>>>> " + blogInfo.getPublished());

        //设置：发布状态
        if(blogInfo.getPublished() == null || blogInfo.getPublished().equals("")) {
            blog.setPublished(true);         //发布
        } else if (blogInfo.getPublished().equals("false")){
            blog.setPublished(false);        //不发布
        }

        //设置：推荐
        if(blogInfo.getRecommend() == null || blogInfo.getRecommend().equals("")){
            blog.setRecommend(false);
        }else if(blogInfo.getRecommend().equals("on")){
            blog.setRecommend(true);
        }

        //设置：评论
        if(blogInfo.getCommentable() == null || blogInfo.getCommentable().equals("")){
            blog.setCommentable(false);
        }else if(blogInfo.getCommentable().equals("on")){
            blog.setCommentable(true);
        }

        //设置：赞赏
        if(blogInfo.getAppreciation() == null || blogInfo.getAppreciation().equals("")){
            blog.setAppreciation(false);
        }else if(blogInfo.getAppreciation().equals("on")){
            blog.setAppreciation(true);
        }

        //设置版权
        if(blogInfo.getShareStatement() == null || blogInfo.getShareStatement().equals("")){
            blog.setShareStatement(false);
        }else if(blogInfo.getShareStatement().equals("on")){
            blog.setShareStatement(true);
        }

        Type type = new Type();                 //设置：类型id
        type.setId(blogInfo.getTypeId());
        blog.setType(type);

        User user = new User();                 //设置：用户id
        user.setId(blogInfo.getUid());
        blog.setUser(user);
        blogMapper.insertBlogByBlog(blog);

        return blog.getId();
    }

    /**
    * @Description: 10、更新：通过BlogInfo对象
    * @Param: [blogInfo]
    * @return:
    */
    @Override
    public int modifyBlogByBlog(BlogInfo blogInfo) {
        Blog blog = new Blog();
        blog.setId(blogInfo.getBid());
        blog.setTitle(blogInfo.getTitle());
        blog.setContent(blogInfo.getContent());
        blog.setFlag(blogInfo.getFlag());
        blog.setFirstPicture(blogInfo.getFirstPicture());
        blog.setDescription(blogInfo.getDescription());

        Type type = new Type();                 //设置：类型id
        type.setId(blogInfo.getTypeId());
        blog.setType(type);
        blog.setUpdateTime(new Date());

        //设置：发布状态
        if(blogInfo.getPublished() == null || blogInfo.getPublished().equals("")){
            blog.setPublished(true);         //发布
        }else if(blogInfo.getPublished().equals("false")){
            blog.setPublished(false);        //不发布
        }


        //设置：推荐
        if(blogInfo.getRecommend() == null || blogInfo.getRecommend().equals("")){
            blog.setRecommend(false);
        }else if(blogInfo.getRecommend().equals("on")){
            blog.setRecommend(true);
        }

        //设置：评论
        if(blogInfo.getCommentable() == null || blogInfo.getCommentable().equals("")){
            blog.setCommentable(false);
        }else if(blogInfo.getCommentable().equals("on")){
            blog.setCommentable(true);
        }

        //设置：赞赏
        if(blogInfo.getAppreciation() == null || blogInfo.getAppreciation().equals("")){
            blog.setAppreciation(false);
        }else if(blogInfo.getAppreciation().equals("on")){
            blog.setAppreciation(true);
        }

        //设置版权
        if(blogInfo.getShareStatement() == null || blogInfo.getShareStatement().equals("")){
            blog.setShareStatement(false);
        }else if(blogInfo.getShareStatement().equals("on")){
            blog.setShareStatement(true);
        }

        return blogMapper.updateBlogByBlog(blog);
    }

    /**
    * @Description: 11、删除博客：通过blogId
    * @Param: [id]
    * @return:
    */
    @Override
    public int removeBlogByBlogId(Long id) {
        return blogMapper.deleteBlogByBlogId(id);
    }

    /**
    * @Description: 12、查询：所有博客信息
    * @Param: []
    * @return:
    */
    @Override
    public List<Blog> findAllBlogList() {
        return blogMapper.selectAllBlogList();
    }

    /**
    * @Description: 13、查询：所有的BlogVO
    * @Param: [searchBlogInfo]
    * @return:
    */
    @Override
    public List<BlogVO> findAllBlogVOListBySearchBlogInfo(SearchBlogInfo searchBlogInfo) {

        // 查询：通过Search对象（title、typeName, recommend）, 查询博客信息
        List<Blog> blogs = blogMapper.selectBlogListBySearchBlogInfo(searchBlogInfo);
        List<BlogVO> blogVOs = new ArrayList<>();

        for(Blog blog:blogs){
            BlogVO blogVO = new BlogVO();
            BeanUtils.copyProperties(blog, blogVO);
            blogVO.setTypeName(blog.getType().getName());
            blogVOs.add(blogVO);
        }
        return blogVOs;
    }

    /**
    * @Description: 14、更新博客按钮：通过(SwitchVO 按钮切换类)
    * @Param: [switchVO]
    * @return:
    */
    @Override
    public int modifyBlogBySwitchVO(SwitchVO switchVO) {
        return blogMapper.updateBlogBySwitchVO(switchVO);
    }

    /**
    * @Description: 15、查询：博客总数目
    * @Param: []
    * @return:
    */
    @Override
    public Long findAllBlogNum() {
        return blogMapper.selectAllBlogNum();
    }

}
