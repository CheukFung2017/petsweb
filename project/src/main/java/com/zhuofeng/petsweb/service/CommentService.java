package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TComment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    public Integer addComment(TComment comment);

    public List<TComment> listComment(Map<String, Object> map);
}
