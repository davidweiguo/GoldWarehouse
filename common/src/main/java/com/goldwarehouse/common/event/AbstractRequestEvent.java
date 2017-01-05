package com.goldwarehouse.common.event;

/**
 * Created by guo_d on 2017/1/5.
 */
public abstract class AbstractRequestEvent extends RemoteAsyncEvent {
    private String txId;

    public AbstractRequestEvent(BasicRequestParameter requestParameter) {
        super(requestParameter.getKey(), requestParameter.getReceiver());
        this.txId = requestParameter.getTxId();
    }

    public String getTxId() {
        return txId;
    }
}
