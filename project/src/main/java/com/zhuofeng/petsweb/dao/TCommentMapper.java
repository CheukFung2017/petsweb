package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TCommentMapper {
    int deleteByPrimaryKey(Integer commentId);

    int insert(TComment record);

    int insertSelective(TComment record);

    TComment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKey(TComment record);


    public List<TComment> listComment(Map<String, Object> map);

    public Long getTotalByPostId(Integer postId);



}