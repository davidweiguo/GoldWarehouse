package com.goldwarehouse.common.event;

/**
 * Created by guo_d on 2017/1/5.
 */
public class BasicReplyParameter extends BasicRequestParameter {
    private boolean ok;
    private String message;

    public BasicReplyParameter(String key, String receiver,
                               String txId, boolean ok, String message) {
        super(key, receiver, txId);
        this.ok = ok;
        this.message = message;
    }

    public boolean isOk() {
        return ok;
    }

    public String getMessage() {
        return message;
    }
}
