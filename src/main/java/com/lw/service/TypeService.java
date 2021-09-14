package com.lw.service;

import com.lw.pojo.Type;
import com.lw.vo.TypeVO;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author L1W31
 * @Version 1.0
 * @Description
 */
public interface TypeService {
    //1、查询：所有的types（不包含blogs）
    List<Type> findAllTypeListWithSort();

    //2、查询：分类的数目
    Long findTypeNum();

    //3、查询：type的（前端传输对象）typeVO集合
    List<TypeVO> findAllTypeVOList();

    //4、修改：分类（名称）
    int updateTypeName(Long id, String newTypeName);

    //5、添加：分类
    int insertTypeByTypeName(String typeName);

    //6、判断：分类是否可以进行删除
    boolean isRemoveTypeByTypeId(Long id);

    //7、查询所有的Type：不用排序
    List<Type> findAllTypeListWithoutSort();
}
