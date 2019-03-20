package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TFindpet;

public interface TFindpetMapper {
    int deleteByPrimaryKey(Integer findpetId);

    int insert(TFindpet record);

    int insertSelective(TFindpet record);

    TFindpet selectByPrimaryKey(Integer findpetId);

    int updateByPrimaryKeySelective(TFindpet record);

    int updateByPrimaryKey(TFindpet record);
}