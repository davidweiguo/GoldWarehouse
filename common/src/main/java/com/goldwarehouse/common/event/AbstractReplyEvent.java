package com.goldwarehouse.common.event;

/**
 * Created by guo_d on 2017/1/5.
 */
public abstract class AbstractReplyEvent extends RemoteAsyncEvent {
    private BasicReplyParameter replyParameter;

    public AbstractReplyEvent(BasicReplyParameter replyParameter) {
        super(replyParameter.getKey(), replyParameter.getReceiver());
        this.replyParameter = replyParameter;
    }

    public boolean isOk() {
        return replyParameter.isOk();
    }

    public String getTxId() {
        return replyParameter.getTxId();
    }

    public String getMsg() {
        return replyParameter.getMessage();
    }
}
