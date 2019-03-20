package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TUserMapper;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Override
    public Integer addUser(TUser user){
        return tUserMapper.insert(user);
    }

    @Override
    public Integer deleteUser(Integer id){
        return  tUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public TUser findById(Integer id){
        return tUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public  Integer updateUser(TUser user){
        return tUserMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<TUser> listUser(Map<String, Object> map){
        return tUserMapper.listUser(map);
    }

    @Override
    public TUser findByUserName(String username){
        return tUserMapper.queryUserByUserName(username);
    }
    @Override
    public TUser findByUserEmail(String email){
        return  tUserMapper.queryUserByEmail(email);
    }

}
