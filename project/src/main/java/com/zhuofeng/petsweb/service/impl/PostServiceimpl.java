package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TPhotoMapper;
import com.zhuofeng.petsweb.dao.TPostMapper;
import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPhoto;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.AdoptService;
import com.zhuofeng.petsweb.service.PhotoService;
import com.zhuofeng.petsweb.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceimpl implements PostService {

    @Autowired
    TPostMapper tPostMapper;
    @Autowired
    TPhotoMapper tPhotoMapper;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private AdoptService adoptService;

    @Override
    @Transactional
    public int insertAdoption(TUser tUser, TPost tPost, TAdoption tAdoption){
         int userId = tUser.getUserId();
         tPost.setAuthorId(userId);
         long tagId = System.currentTimeMillis();
         tPost.setTagId(tagId);
         tPostMapper.insertSelective(tPost);
          TPost t = tPostMapper.selectByTag(tagId);
        System.out.println(t);
        System.out.println(t.getTitle());
        int postId=t.getPostId();
        tAdoption.setPostId(postId);
        if(tPost.getPhotos()!=null) {
            List<TPhoto> tPhotos = tPost.getPhotos();
            for (TPhoto tPhoto : tPhotos) {
                tPhoto.setPostId(postId);
            }
            photoService.insertPhoto(tPhotos);
        }
        adoptService.insertAdoption(tAdoption);
        return 0;

    }

}
