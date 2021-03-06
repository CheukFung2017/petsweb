package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TApply;
import com.zhuofeng.petsweb.entity.TUser;

import java.util.List;

public interface AdoptService {
    //列出所有领养帖子
    public List<TAdoption> listAdoption();

    List<TAdoption> listAdoption_COUNT();

    Integer insertAdoption(TAdoption tAdoption);

    Integer updateAdoption(TAdoption tAdoption);

    int insertAdopt(TUser user,TAdoption tAdoption);

    int modifyAdopt(TAdoption tAdoption);

    TAdoption fingByPostId(Integer postId);

    int insertApply(TApply apply);

}
