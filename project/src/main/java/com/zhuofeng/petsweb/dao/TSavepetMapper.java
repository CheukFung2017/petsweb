package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TSavepet;

public interface TSavepetMapper {
    int deleteByPrimaryKey(Integer savepetId);

    int insert(TSavepet record);

    int insertSelective(TSavepet record);

    TSavepet selectByPrimaryKey(Integer savepetId);

    int updateByPrimaryKeySelective(TSavepet record);

    int updateByPrimaryKey(TSavepet record);
}