package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;

import java.util.List;

public interface PostService {
    public TPost getPost(Integer id);

    public Integer updatePost(TPost post);

    public List<TPost> listUserPost(Integer userId);
}

