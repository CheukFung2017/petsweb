package com.zhuofeng.petsweb.util.result;

public class CodeMessage {
    private int code;
    private String message;

    //通用提示码5001XX
    public static CodeMessage SUCCESS = new CodeMessage(0,"success");
    public static CodeMessage SERVER_ERROR = new CodeMessage(500100,"服务端异常");
    public static CodeMessage BIND_ERROR = new CodeMessage(500101,"参数校验异常");
    public static CodeMessage REQUEST_ILLEGAL = new CodeMessage(500102,"请求非法");
    public static CodeMessage ACCESS_FREQUENCY = new CodeMessage(500103,"请求过于频繁");
    public static CodeMessage ID_EMPTY = new CodeMessage(500104,"编号不能为空");
    public static CodeMessage PERMISSION_DENIED = new CodeMessage(500105,"权限不足");
    public static CodeMessage ID_EXISTS = new CodeMessage(500106,"编号已存在");

    //登录模块5002XX
    public static CodeMessage MESSAGE_EMPTY = new CodeMessage(500200,"账号密码不能为空");
    public static CodeMessage LOGIN_ERROR = new CodeMessage(500201,"账号或密码错误");
    public static CodeMessage NO_LOGIN_PERMISSION = new CodeMessage(500201,"没有登录权限");

    //商品模块5003XX
    public static CodeMessage GOODS_EXISTED = new CodeMessage(500300,"商品编号已存在");
    public static CodeMessage GOODS_NOT_EXISTED = new CodeMessage(500301,"商品不存在");

    //采购单模块5004XX
    public static CodeMessage ORDER_NOT_EXIST = new CodeMessage(500400,"采购单不存在");
    public static CodeMessage UPDATE_ERROR = new CodeMessage(500401,"修改失败");


    public CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CodeMessage[code=" + code + ", message=" + message + "]";
    }
}
