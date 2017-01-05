package com.goldwarehouse.common.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MessageLookup {
    private static final Logger log = LoggerFactory
            .getLogger(MessageLookup.class);
    private static final Map<ErrorMessage, MessageBean> map = new HashMap<>();
    private static final String MSG_SEPARATOR = "|&|";

    private static void addAndCheck(ErrorMessage m, MessageBean bean) throws Exception {
        if (map.put(m, bean) != null) {
            throw new Exception("Critical error: error code duplicate: " + bean.getCode());
        }
    }

    private static MessageBean getBean(int code, String msg) {
        MessageBean bean = new MessageBean(code, msg);
        return bean;
    }

    static {
        try {
            // exception messagg start with 900
            addAndCheck(ErrorMessage.EXCEPTION_MESSAGE, getBean(900, "Unrecognized Error"));
            addAndCheck(ErrorMessage.NONE_MATCHED_MESSAGE, getBean(901, "None matched message"));
            addAndCheck(ErrorMessage.EMPTY_MESSAGE, getBean(902, "Empty message"));

            // system errors start with 100
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static String buildEventMessage(ErrorMessage m, String localMessage) {
        MessageBean mb = lookup(m);
        if (null == mb) {
            mb = lookup(ErrorMessage.NONE_MATCHED_MESSAGE);
        }
        if (null == localMessage) {
            localMessage = "";
        }
        String eventMsg = mb.getCode() + MSG_SEPARATOR + mb.getMsg() + MSG_SEPARATOR + localMessage;
        log.info(eventMsg);
        return eventMsg;
    }

    public static MessageBean lookup(ErrorMessage m) {
        return map.get(m);
    }
}
