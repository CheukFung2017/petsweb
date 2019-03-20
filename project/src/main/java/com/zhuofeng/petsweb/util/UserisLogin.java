package com.zhuofeng.petsweb.util;

import com.zhuofeng.petsweb.entity.TUser;

import javax.servlet.http.HttpSession;

public class UserisLogin {
    public static TUser getUser(HttpSession session){
        TUser user = (TUser) session.getAttribute("user");
        System.out.println(user);
        return user;
    }
}
