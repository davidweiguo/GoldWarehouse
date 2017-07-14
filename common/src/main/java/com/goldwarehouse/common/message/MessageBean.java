package com.goldwarehouse.common.message;

public class MessageBean {
    private int code;
    private String msg;

    public MessageBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
