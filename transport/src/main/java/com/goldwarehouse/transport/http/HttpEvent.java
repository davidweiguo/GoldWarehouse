package com.goldwarehouse.transport.http;

import com.goldwarehouse.common.event.AsyncEvent;
import com.goldwarehouse.common.event.IRemoteEventManager;
import com.goldwarehouse.common.util.IdGenerator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpMethod;

public abstract class HttpEvent {
    public abstract boolean request(IRemoteEventManager eventManager) throws Exception;

    public abstract boolean response(AsyncEvent event) throws Exception;

    protected String sender = "http-bridge";
    protected String id = IdGenerator.getInstance().getNextID();
    protected ChannelHandlerContext ctx;
    protected long created = System.currentTimeMillis();
    protected String uriData;
    protected String content;
    private HttpMethod method;
    private boolean hasResponseEvent = true;

    public HttpEvent() {
    }

    public HttpEvent(boolean hasResponseEvent) {
        this.hasResponseEvent = hasResponseEvent;
    }

    public boolean isTimeout(long timeout) {
        return System.currentTimeMillis() - created > timeout * 1000;
    }

    public void setRequestData(ChannelHandlerContext ctx, String uriData, String content, String sender, HttpMethod method) {
        this.ctx = ctx;
        this.uriData = uriData;
        this.content = content;
        this.sender = sender;
        this.method = method;
    }

    public String getId() {
        return id;
    }

    public boolean isGet() {
        return method == HttpMethod.GET;
    }

    public boolean isPost() {
        return method == HttpMethod.POST;
    }

    public boolean hasResponseEvent() {
        return hasResponseEvent;
    }
}
