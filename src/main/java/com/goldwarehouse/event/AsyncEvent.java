package com.goldwarehouse.event;

import java.io.Serializable;

/**
 * Created by David on 2016/12/20.
 */
public abstract class AsyncEvent implements Serializable {
    private String key;
    private EventPriority priority = EventPriority.NORMAL;

    public AsyncEvent() {
    }

    public AsyncEvent(String key) {
        this();
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @SuppressWarnings("unchecked")
    public static <T extends AsyncEvent> T getEvent(Class<T> t, AsyncEvent event) {
        if (event.getClass().equals(t)) {
            return (T)event;
        }
        return null;
    }

    public EventPriority getPriority() {
        return priority;
    }

    public void setPriority(EventPriority priority) {
        this.priority = priority;
    }
}
