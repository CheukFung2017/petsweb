package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TAdoption;

import java.util.List;

public interface AdoptService {
    //列出所有领养帖子
    public List<TAdoption> listAdoption();

    List<TAdoption> listAdoption_COUNT();

    Integer insertAdoption(TAdoption tAdoption);

}