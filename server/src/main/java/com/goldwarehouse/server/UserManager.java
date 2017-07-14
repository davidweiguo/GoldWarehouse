package com.goldwarehouse.server;

import com.goldwarehouse.common.IPlugin;
import com.goldwarehouse.common.event.AsyncEventProcessor;
import com.goldwarehouse.common.event.BasicReplyParameter;
import com.goldwarehouse.common.event.IAsyncEventManager;
import com.goldwarehouse.common.event.IRemoteEventManager;
import com.goldwarehouse.common.event.account.UserLoginEvent;
import com.goldwarehouse.common.event.account.UserLoginReplyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by guo_d on 2017/1/12.
 */
public class UserManager implements IPlugin {

    private static final Logger log = LoggerFactory.getLogger(UserManager.class);

    @Autowired
    private IRemoteEventManager eventManager;

    private AsyncEventProcessor eventProcessor = new AsyncEventProcessor() {
        @Override
        public void subscribeToEvents() {
            subscribeToEvent(UserLoginEvent.class, null);
        }

        @Override
        public IAsyncEventManager getEventManager() {
            return eventManager;
        }
    };

    @Override
    public void init() throws Exception {
        eventProcessor.setHandler(this);
        eventProcessor.init();
        if (eventProcessor.getThread() != null) {
            eventProcessor.getThread().setName(UserManager.class.getName());
        }
    }

    public void processUserLoginEvent(UserLoginEvent event) throws Exception {
        log.info("Received UserLoginEvent, UserId: " + event.getUserId());
        BasicReplyParameter replyParm = new BasicReplyParameter(event.getKey(), event.getSender(), event.getTxId(), true, "");
        UserLoginReplyEvent replyEvent = new UserLoginReplyEvent(replyParm);
        eventManager.sendRemoteEvent(replyEvent);
    }

    @Override
    public void uninit() {
        eventProcessor.uninit();
    }
}
