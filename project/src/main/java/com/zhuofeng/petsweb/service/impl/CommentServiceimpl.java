package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TCommentMapper;
import com.zhuofeng.petsweb.entity.TComment;
import com.zhuofeng.petsweb.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentServiceimpl implements CommentService {

    @Autowired
    private TCommentMapper commentMapper;

    @Override
    public Integer addComment(TComment comment){
        return commentMapper.insert(comment);
    }

    @Override
    public List<TComment> listComment(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return commentMapper.listComment(map);
    }


}
