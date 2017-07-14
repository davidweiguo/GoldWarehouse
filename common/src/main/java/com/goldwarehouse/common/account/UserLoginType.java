package com.goldwarehouse.common.account;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by guo_d on 2016/12/29.
 */
public enum UserLoginType {
    USER_ID(0),
    EMAIL(1),
    PHONE(2);

    private final int value;

    UserLoginType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static Map<Integer, UserLoginType> map = new HashMap<>();

    static {
        for (UserLoginType status : values()) {
            map.put(status.getValue(), status);
        }
    }

    public static UserLoginType fromInt(int value) {
        return map.get(value);
    }
}
