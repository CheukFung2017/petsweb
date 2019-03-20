package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TKnowledge;

public interface TKnowledgeMapper {
    int deleteByPrimaryKey(Integer knowledgeId);

    int insert(TKnowledge record);

    int insertSelective(TKnowledge record);

    TKnowledge selectByPrimaryKey(Integer knowledgeId);

    int updateByPrimaryKeySelective(TKnowledge record);

    int updateByPrimaryKey(TKnowledge record);
}