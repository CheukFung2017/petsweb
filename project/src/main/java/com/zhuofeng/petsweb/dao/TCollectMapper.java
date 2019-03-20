package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TCollect;

public interface TCollectMapper {
    int deleteByPrimaryKey(Integer collectId);

    int insert(TCollect record);

    int insertSelective(TCollect record);

    TCollect selectByPrimaryKey(Integer collectId);

    int updateByPrimaryKeySelective(TCollect record);

    int updateByPrimaryKey(TCollect record);
}