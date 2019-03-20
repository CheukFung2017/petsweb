package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;

public interface PostService {
    public int insertAdoption(TUser tUser, TPost tPost, TAdoption tAdoption);
}
