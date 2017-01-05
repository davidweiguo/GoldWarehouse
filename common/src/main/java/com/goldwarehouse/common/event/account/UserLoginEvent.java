package com.goldwarehouse.common.event.account;

import com.goldwarehouse.common.account.UserLoginType;
import com.goldwarehouse.common.event.EventPriority;
import com.goldwarehouse.common.event.RemoteAsyncEvent;

/**
 * Created by guo_d on 2016/12/29.
 */
public class UserLoginEvent extends RemoteAsyncEvent {
    private String userId;
    private String password;
    private UserLoginType loginType;

    public UserLoginEvent(String key, String receiver, String userId, String password) {
        super(key, receiver);
        this.userId = userId;
        this.password = password;
        setPriority(EventPriority.HIGH);
    }

    public UserLoginEvent(String key, String receiver, String userId, String password, UserLoginType loginType) {
        super(key, receiver);
        this.userId = userId;
        this.password = password;
        this.loginType = loginType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
