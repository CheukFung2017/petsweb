package com.zhuofeng.petsweb;

import com.zhuofeng.petsweb.dao.TAdoptionMapper;
import com.zhuofeng.petsweb.dao.TPostMapper;
import com.zhuofeng.petsweb.dao.TUserMapper;
import com.zhuofeng.petsweb.entity.TAdoption;

import com.zhuofeng.petsweb.entity.TPhoto;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.PostService;
import com.zhuofeng.petsweb.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PetswebApplicationTests {

    @Autowired
    PostService postService;

    @Test
    public void addAdopt() {
//        TUser user = new TUser();
//        user.setUserName("Antony");
//        user.setUserId(6);
//        TPost tPost = new TPost();
//        tPost.setTitle("this is title2");
//        tPost.setPostId(8);
//        tPost.setUpdated("2019-02-10");
//        TAdoption tAdoption = new TAdoption();
//        tAdoption.setPetName("Apple");
//        List<TPhoto> tPhotos = new ArrayList<TPhoto>();
//        TPhoto p1 = new TPhoto();
//        p1.setPhotoAddress("3.jpg");
//        TPhoto p2 = new TPhoto();
//        p2.setPhotoAddress("5.jpg");
//        tPhotos.add(p1);
//        tPhotos.add(p2);
//        tPost.setPhotos(tPhotos);
//        postService.insertAdoption(user,tPost,tAdoption);


    }


}

