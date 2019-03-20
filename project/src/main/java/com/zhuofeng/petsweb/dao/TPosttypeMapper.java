package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TPosttype;

public interface TPosttypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(TPosttype record);

    int insertSelective(TPosttype record);

    TPosttype selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(TPosttype record);

    int updateByPrimaryKey(TPosttype record);
}