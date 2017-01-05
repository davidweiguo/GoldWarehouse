package com.goldwarehouse.common.event;

/**
 * Created by guo_d on 2017/1/5.
 */
public class BasicRequestParameter {
    private String key;
    private String receiver;
    private String txId;

    public BasicRequestParameter(String key, String receiver, String txId) {
        this.key = key;
        this.receiver = receiver;
        this.txId = txId;
    }

    public String getKey() {
        return key;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getTxId() {
        return txId;
    }
}
