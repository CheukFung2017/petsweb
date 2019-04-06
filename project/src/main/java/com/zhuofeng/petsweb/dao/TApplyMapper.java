package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TApply;

public interface TApplyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(TApply record);

    int insertSelective(TApply record);

    TApply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(TApply record);

    int updateByPrimaryKey(TApply record);
}