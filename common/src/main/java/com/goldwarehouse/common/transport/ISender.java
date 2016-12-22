package com.goldwarehouse.common.transport;

/**
 * Created by David on 2016/12/14.
 */
public interface ISender {
    void sendMessage(String message) throws Exception;
}
