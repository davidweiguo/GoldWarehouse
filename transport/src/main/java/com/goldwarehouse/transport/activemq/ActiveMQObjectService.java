package com.goldwarehouse.transport.activemq;

import com.goldwarehouse.common.transport.IObjectListener;
import com.goldwarehouse.common.transport.IObjectSender;
import com.goldwarehouse.common.transport.IObjectTransportService;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ActiveMQObjectService extends ActiveMQService implements IObjectTransportService {
    static Logger log = LoggerFactory.getLogger(ActiveMQObjectService.class);
    private XStream xstream = new XStream(new DomDriver());

    private HashMap<String, ArrayList<IObjectListener>> objSubscribers = new HashMap<>();
    private HashMap<IObjectListener, MessageConsumer> objConsumers = new HashMap<>();


    class ObjectListenerAdaptor implements MessageListener {
        private IObjectListener listener;

        public ObjectListenerAdaptor(IObjectListener listener) {
            this.listener = listener;
        }

        @Override
        public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                try {
                    String str = ((TextMessage) message).getText();
                    log.debug("Received message: \n" + str);
                    Object obj = xstream.fromXML(str);
                    listener.onMessage(obj);
                } catch (JMSException e) {
                    log.error(e.getMessage(), e);
                    e.printStackTrace();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            } else {
                log.error("Unexpected text message: " + message);
            }
        }
    }

    class ObjectSender implements IObjectSender {
        private MessageProducer producer;

        public ObjectSender(MessageProducer producer) {
            this.producer = producer;
        }

        @Override
        public void sendMessage(Object obj) throws Exception {
            String message = xstream.toXML(obj);
            log.debug("Sending message: \n" + message);
            TextMessage txt = session.createTextMessage(message);
            producer.send(txt);
        }
    }

    @Override
    public void createReceiver(String subject, IObjectListener listener)
            throws Exception {
        //only one listener per subject allowed for point to point connection
        MessageConsumer consumer = receivers.get(subject);
        if (null == consumer) {
            Destination dest = session.createQueue(subject);
            consumer = session.createConsumer(dest);
            receivers.put(subject, consumer);
        }
        if (null == listener)
            consumer.setMessageListener(null);
        else
            consumer.setMessageListener(new ObjectListenerAdaptor(listener));
    }

    @Override
    public void createSubscriber(String subject, IObjectListener listener)
            throws Exception {
        //many listeners per subject allowed for publish/subscribe
        ArrayList<IObjectListener> listeners = objSubscribers.get(subject);
        if (null == listeners) {
            listeners = new ArrayList<IObjectListener>();
            objSubscribers.put(subject, listeners);
        }

        if (!listeners.contains(listener)) {
            Destination dest = session.createTopic(subject);
            MessageConsumer consumer = session.createConsumer(dest);
            consumer.setMessageListener(new ObjectListenerAdaptor(listener));
            listeners.add(listener);
            objConsumers.put(listener, consumer);
        }
    }

    @Override
    public void removeSubscriber(String subject, IObjectListener listener)
            throws Exception {
        ArrayList<IObjectListener> listeners = objSubscribers.get(subject);
        if (null == listeners)
            return;

        if (listeners.contains(listener)) {
            MessageConsumer consumer = objConsumers.get(listener);
            if (null == consumer)
                return;
            consumer.setMessageListener(null);
            objConsumers.remove(listener);
            listeners.remove(listener);
        }
    }

    @Override
    public void sendMessage(String subject, Object obj) throws Exception {
        createObjectSender(subject).sendMessage(obj);
    }

    @Override
    public void publishMessage(String subject, Object obj) throws Exception {
        createObjectPublisher(subject).sendMessage(obj);

    }

    @Override
    public IObjectSender createObjectSender(String subject) throws Exception {
        MessageProducer producer = senders.get(subject);
        if (null == producer) {
            Destination dest = session.createQueue(subject);
            producer = session.createProducer(dest);
            producer.setDeliveryMode(persistent);
            senders.put(subject, producer);
        }
        return new ObjectSender(producer);
    }

    @Override
    public IObjectSender createObjectPublisher(String subject) throws Exception {
        MessageProducer producer = publishers.get(subject);
        if (null == producer) {
            Destination dest = session.createTopic(subject);
            producer = session.createProducer(dest);
            producer.setDeliveryMode(persistent);
            publishers.put(subject, producer);
        }
        return new ObjectSender(producer);
    }
}
