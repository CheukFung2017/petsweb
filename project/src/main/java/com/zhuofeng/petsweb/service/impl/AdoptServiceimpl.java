package com.zhuofeng.petsweb.service.impl;

import com.zhuofeng.petsweb.dao.TAdoptionMapper;
import com.zhuofeng.petsweb.dao.TPostMapper;
import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPhoto;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.AdoptService;
import com.zhuofeng.petsweb.service.PhotoService;
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
    @Autowired
    private PhotoService photoService;
    @Autowired
    private AdoptService adoptService;

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

    @Override
    @Transactional
    public int insertAdopt(TUser tUser,TAdoption tAdoption){
        int userId = tUser.getUserId();

        TPost tPost = tAdoption.getPost();
        tPost.setAuthorId(userId);
        long tagId = System.currentTimeMillis();
        tPost.setTagId(tagId);
        tPost.setTypeId(1);
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
        return 1;
    }
}
