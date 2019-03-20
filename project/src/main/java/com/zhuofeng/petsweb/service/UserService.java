package com.zhuofeng.petsweb.service;


import com.zhuofeng.petsweb.entity.TUser;

import java.util.List;
import java.util.Map;

public interface UserService {
    public Integer addUser(TUser user);

    public Integer deleteUser(Integer id);

    public  TUser findById(Integer id);

    public  Integer updateUser(TUser user);

    public List<TUser> listUser(Map<String, Object> map);

    public TUser findByUserName(String username);

    public TUser findByUserEmail(String email);




}
