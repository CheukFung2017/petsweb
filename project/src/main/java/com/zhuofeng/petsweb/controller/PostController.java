package com.zhuofeng.petsweb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.AdoptService;
import com.zhuofeng.petsweb.service.PostService;
import com.zhuofeng.petsweb.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.zhuofeng.petsweb.util.result.CodeMessage.UPDATE_ERROR;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    AdoptService adoptService;

    @RequestMapping("/postdetail")
    public Result<Object> getPostDetail(int postId){
        TPost post = postService.getPost(postId);
        int typeId = post.getTypeId();
        if(typeId==1){
            TAdoption tAdoption = adoptService.fingByPostId(postId);
            return Result.success(tAdoption);
        }
        else
            return Result.error(UPDATE_ERROR);

    }

    @RequestMapping("/listUserPost")
    public Result<Object> listUserPost(HttpSession session,@RequestParam int pageNum, @RequestParam int pageSize){
        String orderby = "t_post.updated desc";

        TUser user = (TUser) session.getAttribute("user");
        int id=user.getUserId();
        PageHelper.startPage(pageNum,pageSize,orderby);
        List<TPost> posts = postService.listUserPost(id);

        PageInfo<TPost> pageInfo=new PageInfo<>(posts);
        return Result.success(pageInfo);
    }

}
