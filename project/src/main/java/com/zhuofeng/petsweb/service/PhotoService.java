package com.zhuofeng.petsweb.service;

import com.zhuofeng.petsweb.entity.TPhoto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PhotoService {

    boolean updatePhoto(List<TPhoto> tPhotos);

    boolean deletePhoto(List<TPhoto> tPhotos);

     int updatePhoto(TPhoto tPhoto);


     boolean insertPhoto(List<TPhoto> tPhotos);


}
