package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TAdoptionMapper;
import com.zhuofeng.petsweb.dao.TPostMapper;
import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.service.AdoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdoptServiceimpl implements AdoptService {
    @Autowired
    private TAdoptionMapper tAdoptionMapper;
    @Autowired
    private TPostMapper tPostMapper;

    @Override
    public List<TAdoption> listAdoption(){
        Map<String,Object> map = new HashMap<>();
        return tAdoptionMapper.listAdoption();
    }

    @Override
    public List<TAdoption> listAdoption_COUNT(){
        return tAdoptionMapper.listAdoption_COUNT();
    }

    @Override
    @Transactional
    public Integer insertAdoption(TAdoption tAdoption){
        return tAdoptionMapper.insertSelective(tAdoption);
    }
}
