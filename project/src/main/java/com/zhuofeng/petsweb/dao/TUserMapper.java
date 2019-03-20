package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    List<TUser> queryAllUser();

    TUser queryUserByUserName(String username);

    TUser queryUserByEmail(String email);

    List<TUser> listUser(Map<String,Object> map);
}