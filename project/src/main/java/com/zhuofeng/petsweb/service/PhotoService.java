package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TPhoto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoService {
//    boolean insertPhoto(List<TPhoto> tPhotos);

    boolean updatePhoto(List<TPhoto> tPhotos);

    boolean deletePhoto(List<TPhoto> tPhotos);

    public int updatePhoto(TPhoto tPhoto);


    public boolean insertPhoto(List<TPhoto> tPhotos);



}
