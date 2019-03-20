package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TPost;
import org.springframework.stereotype.Repository;

@Repository
public interface TPostMapper {
    int deleteByPrimaryKey(Integer postId);

    int insert(TPost record);

    int insertSelective(TPost record);

    TPost selectByPrimaryKey(Integer postId);

    TPost selectByTag(Long tagId);

    int updateByPrimaryKeySelective(TPost record);

    int updateByPrimaryKey(TPost record);
}