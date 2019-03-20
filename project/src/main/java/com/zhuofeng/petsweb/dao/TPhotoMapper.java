package com.zhuofeng.petsweb.dao;

import com.zhuofeng.petsweb.entity.TPhoto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPhotoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPhoto record);

    int insertSelective(TPhoto record);

    TPhoto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPhoto record);

    int updateByPrimaryKey(TPhoto record);

    int insertAllPhotos(@Param("t_photos") List<TPhoto> tPhotos);
}