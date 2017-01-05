package com.goldwarehouse.common.event.account;

import com.goldwarehouse.common.event.AbstractReplyEvent;
import com.goldwarehouse.common.event.BasicReplyParameter;

/**
 * Created by guo_d on 2016/12/29.
 */
public class UserLoginReplyEvent extends AbstractReplyEvent {

    public UserLoginReplyEvent(BasicReplyParameter replyParameter) {
        super(replyParameter);
    }
}
