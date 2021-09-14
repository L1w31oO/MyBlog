package com.lw.dao;

import com.lw.pojo.Type;
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
public interface TypeMapper {
    //1、查询：所有（博客类型）
    List<Type> selectAllBlogTypeList();

    //2、通过：typeId查询， type
    Type selectTypeByTypeId(Long id);

    //3、查询：所有type（不包含blog）
    List<Type> selectAllTypeList();

    //4、查询：分类的数目
    Long selectTypeNum();

    //5、修改：分类（名称）
    int updateTypeByType(Type type);

    //6、查询：通过分类名称
    Type selectTypeByTypeName(String typeName);

    //7、添加：分类
    int insertTypeByTypeName(String typeName);

    //8、删除分类：通过typeId
    int deleteTypeByTypeId(Long id);

}