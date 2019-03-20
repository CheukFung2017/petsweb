package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TCourtship;

public interface TCourtshipMapper {
    int deleteByPrimaryKey(Integer courtshipId);

    int insert(TCourtship record);

    int insertSelective(TCourtship record);

    TCourtship selectByPrimaryKey(Integer courtshipId);

    int updateByPrimaryKeySelective(TCourtship record);

    int updateByPrimaryKey(TCourtship record);
}