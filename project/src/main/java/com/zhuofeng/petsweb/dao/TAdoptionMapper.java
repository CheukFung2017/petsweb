package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TAdoption;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TAdoptionMapper {
    int deleteByPrimaryKey(Integer adoptId);

    int insert(TAdoption record);

    int insertSelective(TAdoption record);

    TAdoption selectByPrimaryKey(Integer adoptId);

    int updateByPrimaryKeySelective(TAdoption record);

    int updateByPrimaryKey(TAdoption record);

    ArrayList<TAdoption> listAdoption();

    ArrayList<TAdoption> listAdoption_COUNT();
}