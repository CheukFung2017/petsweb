package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TPhotoMapper;
import com.zhuofeng.petsweb.entity.TPhoto;
import com.zhuofeng.petsweb.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhotoServiceimpl implements PhotoService {
    @Autowired
    private TPhotoMapper tPhotoMapper;

    @Override
    @Transactional
    public boolean insertPhoto(List<TPhoto> tPhotos){
        tPhotoMapper.insertAllPhotos(tPhotos);
        return true;
    }

    @Override
    public boolean updatePhoto(List<TPhoto> tPhotos){
        return false;
    }

    @Override
    public boolean deletePhoto(List<TPhoto> tPhotos){
        return false;
    }

    @Override
    public int updatePhoto(TPhoto tPhoto){
        return tPhotoMapper.updateByPrimaryKeySelective(tPhoto);
    }

}
