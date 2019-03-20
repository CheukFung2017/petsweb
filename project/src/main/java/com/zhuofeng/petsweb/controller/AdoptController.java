package com.zhuofeng.petsweb.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuofeng.petsweb.entity.TAdoption;
import com.zhuofeng.petsweb.entity.TPhoto;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.AdoptService;
import com.zhuofeng.petsweb.service.PostService;
import com.zhuofeng.petsweb.util.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/Adopt")
@ResponseBody
public class AdoptController {
    @Autowired
    private AdoptService adoptService;

    @Autowired
    private PostService postService;


    //首页展示领养信息

    /**
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/home_adopt")
    public Result<Object> toList(int pageNum, int pageSize){
        String orderby = "t_post.updated desc";
//        String orderby="t_post.phone";
        List<TAdoption> tAdoptions = adoptService.listAdoption();
        PageHelper.startPage(pageNum,pageSize,orderby);
//        List<TAdoption> tAdoptions1 = adoptService.listAdoption_COUNT();

        PageInfo<TAdoption> pageInfo=new PageInfo<>(tAdoptions);
        return Result.success(pageInfo);

    }


    //新建领养文章

    /**
     *
     * @param session
     * @param tPost
     * @param tAdoption
     * @return
     */
    @RequestMapping("/add")
    public Result<TAdoption> addAdoption(HttpSession session, @RequestBody TPost tPost, @RequestBody TAdoption tAdoption){
        TUser user = (TUser)session.getAttribute("user");
        postService.insertAdoption(user,tPost,tAdoption);
        return Result.success(tAdoption);

    }




}
