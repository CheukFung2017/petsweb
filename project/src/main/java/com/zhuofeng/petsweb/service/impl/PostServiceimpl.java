package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TPhotoMapper;
import com.zhuofeng.petsweb.dao.TPostMapper;
import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPhoto;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.AdoptService;
import com.zhuofeng.petsweb.service.PhotoService;
import com.zhuofeng.petsweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceimpl implements PostService {

    @Autowired
    TPostMapper tPostMapper;
    @Autowired
    TPhotoMapper tPhotoMapper;

    @Override
    public TPost getPost(Integer id){
        return tPostMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updatePost(TPost post){
        return tPostMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public List<TPost> listUserPost(Integer userId){
        Map<String,Object> map = new HashMap<>();
        return tPostMapper.listUserPost(userId);
    }

}
