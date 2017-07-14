package com.goldwarehouse.common.event.account;

import com.goldwarehouse.common.account.UserLoginType;
import com.goldwarehouse.common.event.AbstractRequestEvent;
import com.goldwarehouse.common.event.BasicRequestParameter;
import com.goldwarehouse.common.event.EventPriority;
import com.goldwarehouse.common.event.RemoteAsyncEvent;

/**
 * Created by guo_d on 2016/12/29.
 */
public class UserLoginEvent extends AbstractRequestEvent {
    private String userId;
    private String password;
    private UserLoginType loginType;

    public UserLoginEvent(BasicRequestParameter requestParameter, String userId, String password) {
        super(requestParameter);
        this.userId = userId;
        this.password = password;
        setPriority(EventPriority.HIGH);
    }

    public UserLoginEvent(BasicRequestParameter requestParameter, String userId, String password, UserLoginType loginType) {
        super(requestParameter);
        this.userId = userId;
        this.password = password;
        this.loginType = loginType;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
