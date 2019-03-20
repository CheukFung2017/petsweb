package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TComment;

public interface TCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(TComment record);

    int insertSelective(TComment record);

    TComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKey(TComment record);
}