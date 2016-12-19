package com.goldwarehouse.transport.activemq;

/**
 * Created by David on 2016/12/14.
 */
public interface IMessageListener {
    void onMessage(String message);
}
