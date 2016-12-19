package com.goldwarehouse.transport.activemq;

/**
 * Created by David on 2016/12/14.
 */
public interface ITransportService {
    void startBroker()throws Exception;
    void closeBroker() throws Exception;
    void startService() throws Exception;
    void closeService() throws Exception;
    ISender createSender(String subject) throws Exception;
    void createReceiver(String subject, IMessageListener listener) throws Exception;
    void removeReceiver(String subject) throws Exception;
    ISender createPublisher(String subject) throws Exception;
    void createSubscriber(String subject, IMessageListener listener) throws Exception;
    void removeSubscriber(String subject, IMessageListener listener) throws Exception;
    void sendMessage(String subject, String message) throws Exception;
    void publishMessage(String subject, String message) throws Exception;
}
