package com.zhuofeng.petsweb.util.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    /**
     *  成功时候的调用
     *
     * @param data*/
    public static  <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     *  失败时候的调用
     * */
    public static  <T> Result<T> error(CodeMessage codeMessage){
        return new Result<T>(codeMessage);
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(CodeMessage codeMessage) {
        if(codeMessage != null) {
            this.code = codeMessage.getCode();
            this.msg = codeMessage.getMessage();
        }
    }


    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
