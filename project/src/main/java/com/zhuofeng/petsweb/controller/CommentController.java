package com.zhuofeng.petsweb.controller;


import com.zhuofeng.petsweb.entity.TComment;
import com.zhuofeng.petsweb.entity.TPost;
import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.CommentService;
import com.zhuofeng.petsweb.service.PostService;
import com.zhuofeng.petsweb.service.UserService;
import com.zhuofeng.petsweb.util.UserisLogin;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;


    /**
     *
     * @param comment
     * @param session
     * @return
     */
    @RequestMapping(value="/save",produces="application/json;charset=UTF-8")
    public String addComment(TComment comment, HttpSession session){
        int resultTotal = 0; //标识评论更新

        int res = 0;	//标识文章更新

        JSONObject result = new JSONObject();

        TUser user = UserisLogin.getUser(session);

        if(user==null){
            result.put("success",false);
            result.put("msg", "用户没有登录");
            return result.toString();
        }

        if(!user.getUserId().equals(comment.getUserId())){
            result.put("success", false);
            result.put("msg", "不能以别人的名义发表评论");
            return result.toString();
        }

        //增加对应文章的replayCount
        TPost post = postService.getPost(comment.getPostId());

        if(post==null){
            result.put("success", false);
            result.put("msg", "传入的评论对应文章id不存在");
            return result.toString();
        }

        try {
            resultTotal = commentService.addComment(comment);
        } catch (Exception e) {
            // TODO: handle exception
            result.put("success", false);
            result.put("msg", "保存过程中出现错误");
            return result.toString();
        }

//        article.setReplyCount(article.getReplyCount()+1);
        if(post.getReplycount()==null){
            post.setReplycount(0);
        }
//        System.out.println(post.getReplycount());
        post.setReplycount(post.getReplycount()+1);

        res = postService.updatePost(post);

        if(resultTotal > 0 && res > 0) {
            result.put("success", true);
            result.put("msg", "评论成功");
        } else {
            result.put("success", false);
            result.put("msg", "评论失败");
        }

        return result.toString();
    }


    @RequestMapping(value = "/list",produces = "application/json;charset=UTF-8")
    public String listCommentByPostId(@RequestParam(value = "postId",required = false) Integer postId,@RequestParam(value = "userId",required = false)Integer userId,HttpSession session){
        JSONObject jsonObject = new JSONObject();

        Map<String,Object> map = new HashMap<String,Object>();

        if(postId!=null){
            TPost post = postService.getPost(postId);
            if(post==null){
                jsonObject.put("success", false);
                jsonObject.put("msg", "没有这篇文章");
                return jsonObject.toString();
            }
            map.put("postId",postId);
        }
        if(userId!=null){
            TUser user = userService.findById(userId);
            if(user==null){
                jsonObject.put("success", false);
                jsonObject.put("msg", "用户不存在");
                return jsonObject.toString();
            }
            map.put("userId",userId);
        }

        List<TComment> comments = commentService.listComment(map);

        JSONArray array = JSON.parseArray(JSONObject.toJSONString(comments,
                SerializerFeature.DisableCircularReferenceDetect));

        Integer total = comments.size();

        jsonObject.put("success", true);

        jsonObject.put("total", total);

        jsonObject.put("comments", array);

        return jsonObject.toString();
    }
}
