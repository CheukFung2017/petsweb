package com.zhuofeng.petsweb.controller;

import com.zhuofeng.petsweb.entity.TUser;
import com.zhuofeng.petsweb.service.UserService;
import com.zhuofeng.petsweb.util.AccountMatch;
import com.zhuofeng.petsweb.util.Md5Encrypt;
import com.zhuofeng.petsweb.util.UserisLogin;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登陆
     * @param username
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                            HttpSession session) {


        JSONObject jsonObject = new JSONObject();

        TUser user;

        if (AccountMatch.isEmail(username)) {//如果是邮箱登录
            user = userService.findByUserEmail(username);
        } else {
            user = userService.findByUserName(username);
        }

        if (user == null) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "用户不存在");
        } else {
            if (Md5Encrypt.getMD5(password).equals(user.getPassword())) {
                jsonObject.put("success", true);
                jsonObject.put("msg", "登录成功");
                jsonObject.put("user", user);
                session.setAttribute("user", user);
                System.out.println(session.getId());
            } else {
                jsonObject.put("success", false);
                jsonObject.put("msg", "登录失败：密码错误");
            }
        }

        // String jsonp = callback+"("+jsonObject.toString()+")";

        // System.out.println(jsonp);

        return jsonObject.toString();
    }


    /**
     *用户注册
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userRegister(TUser user, HttpSession session) {

        JSONObject jsonObject = new JSONObject();

        /*
         * 验证邮箱或用户名是否有效
         */

        List<TUser> users;

        Map<String, Object> map = new HashMap<String, Object>();

        if (user.getEmail() != null && !user.getEmail().equals("")) {
            map.put("email", user.getEmail());
            users = userService.listUser(map);
            if (users.size() > 0) {
                jsonObject.put("success", true);
                jsonObject.put("isValid", false);
                jsonObject.put("msg", "邮箱已被占用");
                return jsonObject.toString();
            }
            map.clear(); // 验证完清除map
        }

        if (user.getUserName() != null && !user.getUserName().equals("")) {
            map.put("username", user.getUserName());
            users = userService.listUser(map);
            if (users.size() > 0) {
                jsonObject.put("success", true);
                jsonObject.put("isValid", false);
                jsonObject.put("msg", "用户名已被占用");
                return jsonObject.toString();
            }
            map.clear(); // 验证完清除map
        }

        int res = 0;

        // 用户注册需要填写的信息
        // 用户名,密码,邮箱
        user.setRole(1); // 0为管理员 1为普通用户
//        user.setNickname(user.getUsername()); // 昵称和用户名默认一样.
        user.setPassword(Md5Encrypt.getMD5(user.getPassword()));// md5
        // password后存进user.password

        String base64Img = "https://ws3.sinaimg.cn/large/006OWbT9gy1ftwxpbhzi8j30bo0bogld.jpg";

        user.setProfile(base64Img);

        try {
            res = userService.addUser(user);
        } catch (Exception e) {
            // TODO: handle exception
            jsonObject.put("success", false);
            jsonObject.put("msg", "注册失败 出现问题");
            return jsonObject.toString();
        }

        if (res > 0) {
            jsonObject.put("success", true);
            jsonObject.put("msg", "注册成功");
            jsonObject.put("user", user);
        } else {
            jsonObject.put("success", false);
            jsonObject.put("msg", "注册失败");
        }

        return jsonObject.toString();

    }

    /**
     * 可用于注册时验证用户名或者邮箱是否有效(是否重复).
     * @param username
     * @param email
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/isValid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String searchIfValid(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "email", required = false) String email, HttpSession session) {

        JSONObject jsonObject = new JSONObject();

        Map<String, Object> map = new HashMap<String, Object>();

        if (email != null && !email.equals("")) {
            map.put("email", email);
        } else if (username != null && !username.equals("")) {
            map.put("username", username);
        } else {
            jsonObject.put("success", false);
            jsonObject.put("msg", "传入参数的值为空");
            return jsonObject.toString();
        }

        List<TUser> users = userService.listUser(map);

        if (users.size() > 0) {
            jsonObject.put("success", true);
            jsonObject.put("isValid", false);
            jsonObject.put("msg", "用户名或邮箱已被占用");
        } else {
            jsonObject.put("success", true);
            jsonObject.put("isValid", true);
            jsonObject.put("msg", "用户名或邮箱可用");
        }

        return jsonObject.toString();
    }

    /**
     * 修改用户信息
     *
     * @param oldpassword
     * @param user
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userModify(@RequestParam(value = "oldpassword", required = false) String oldpassword, TUser user,
                             HttpSession session) {
        JSONObject jsonObject = new JSONObject();

        // 先用session判断是否有登录 没有直接拒绝
        // 再判断是不是用户本人或者管理员.(管理员可以用role判断是否是 不能用username判断.如果有多个管理员的话)
        // 0为管理员 1为普通用户
        if (UserisLogin.getUser(session) == null) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "用户没有登录");
            return jsonObject.toString();
        }

        System.out.println(user);

        if (!user.getUserName().equals(UserisLogin.getUser(session).getUserName())
                && UserisLogin.getUser(session).getRole() == 1) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "无权限");
            return jsonObject.toString();
        }
        if (user.getPassword() != null) { // TODO 如果修改了密码,前端页面应该在修改成功后提示重新登录
            // 调用logout
            if (!Md5Encrypt.getMD5(oldpassword).equals(UserisLogin.getUser(session).getPassword())) {
                jsonObject.put("success", false);
                jsonObject.put("msg", "oldpassword error");
                return jsonObject.toString();
            }

            user.setPassword(Md5Encrypt.getMD5(user.getPassword()));// md5
            // 密码之后再存进实体类
        }
        // 修复的数据可以是姓名(用户名不能修改),头像,手机号,密码,邮箱.
        int res = userService.updateUser(user);
        if (res > 0) {
            jsonObject.put("success", true);
            jsonObject.put("msg", "修改成功");
        } else {
            jsonObject.put("success", false);
            jsonObject.put("msg", "修改失败");
        }
        return jsonObject.toString();
    }

    /**
     * 用户退出登陆 清除session
     *
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout", produces = "application/json;charset=UTF-8")
    public String userLogout(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        try {
            session.invalidate();
            System.out.println(session.getId());
            jsonObject.put("success", true);
            jsonObject.put("msg", "用户已安全退出。");
        } catch (Exception e) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "用户安全退出失败");
        }
        return jsonObject.toString();
    }


    /**
     * 查看登录用户个人信息。可用于查看是否登录，与其他接口相联合，例如游客没有登录的话
     * 不能发帖，登录的人没有管理员权限(可以通过role属性判断)的话，不能进行用户删除添加一些相关操作等。
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/userinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userInfo(HttpSession session) {

        JSONObject jsonObject = new JSONObject();

        // User user = UserisLogin.getUser(session);

        System.out.println("userinfo sessionid " + session.getId());

        TUser user = (TUser) session.getAttribute("user");

        // 判断是否有登录
        if (user == null) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "用户没有登录");
            return jsonObject.toString();
        }

        jsonObject.put("success", true);
        jsonObject.put("user", user);

        // String jsonp = callback+"("+jsonObject.toString()+")";

        // System.out.println("jsonp is "+jsonp);

        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/userinfobyid", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String userInfobyId(@RequestParam(value = "id", required = false) String id, HttpSession session) {

        JSONObject jsonObject = new JSONObject();

        if (id == null) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "没有传入用户id");
            return jsonObject.toString();
        }

        TUser user = userService.findById(Integer.parseInt(id));

        // 判断是否存在
        if (user == null) {
            jsonObject.put("success", false);
            jsonObject.put("msg", "用户不存在");
            return jsonObject.toString();
        }
        jsonObject.put("success", true);
        jsonObject.put("user", user);

        return jsonObject.toString();

    }
}