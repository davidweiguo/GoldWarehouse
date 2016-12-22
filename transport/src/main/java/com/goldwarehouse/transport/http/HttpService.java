package com.goldwarehouse.transport.http;

import java.lang.reflect.Constructor;

import com.goldwarehouse.transport.tools.ByteBufToBytes;
import com.goldwarehouse.transport.tools.HttpEventUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.goldwarehouse.common.IPlugin;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpService implements IPlugin {
	private static final Logger log = LoggerFactory.getLogger(HttpService.class);

	@Autowired
	private HttpEventHandler httpEventHandler;

	private final int port;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	public HttpService(int port) {
		this.port = port;
	}

	@Override
	public void init() throws Exception {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					httpEventHandler.init();
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				}
				bossGroup = new NioEventLoopGroup();
				workerGroup = new NioEventLoopGroup();
				try {
					ServerBootstrap bs = new ServerBootstrap();
					bs.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
							.childHandler(new ChannelInitializer<SocketChannel>() {

								@Override
								protected void initChannel(SocketChannel sc) throws Exception {
									ChannelPipeline cp = sc.pipeline();
									cp.addLast("decoder", new HttpRequestDecoder());
									cp.addLast("encoder", new HttpResponseEncoder());
									cp.addLast("aggregator", new HttpObjectAggregator(1024));
									cp.addLast("deflater", new HttpContentCompressor());
									cp.addLast("handler", new HttpServiceHandler());
								}

							});
					Channel ch = bs.bind(port).sync().channel();
					ch.closeFuture().sync();
				} catch (Exception e) {
					log.error(e.getMessage(), e);
				} finally {
					bossGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
				}
			}
		};

		thread.start();
	}

	@Override
	public void uninit() {
		if (bossGroup != null)
			bossGroup.shutdownGracefully();
		if (workerGroup != null)
			workerGroup.shutdownGracefully();
	}

	private class HttpServiceHandler extends SimpleChannelInboundHandler<HttpObject> {
		private static final String pack = "com.cyanspring.event.http.";

		// Check data correction
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
			if (msg instanceof HttpRequest && msg instanceof HttpContent) {
				try {
					String uri;
					String clz;
					String uriData = null;
					HttpRequest request = (HttpRequest) msg;
					uri = request.getUri();
					if (uri.contains("?")) {
						String[] split = uri.split("\\?");
						clz = split[0];
						if (split.length >= 2) 
							uriData = split[1];
					} else {
						clz = uri;
					}
					if (clz != null) {
						clz = clz.substring(1, clz.length());
						Class<?> c = Class.forName(pack + clz);
						@SuppressWarnings("unchecked")
						Constructor<HttpEvent> ctor = (Constructor<HttpEvent>) c.getConstructor();
						HttpEvent event = ctor.newInstance();
						ByteBufToBytes reader = new ByteBufToBytes(((HttpContent) msg).content().capacity());
						reader.reading(((HttpContent) msg).content());
						event.setRequestData(ctx, uriData, new String(reader.readFull()),
								httpEventHandler.getBridgeId(), request.getMethod());
						if (!httpEventHandler.addRequest(event.getId(), event))
							HttpEventUtil.returnFail(ctx, "");
					} else {
						HttpEventUtil.returnFail(ctx, "");
					}
				} catch (Exception e) {
					log.warn(e.getMessage(), e);
					HttpEventUtil.returnFail(ctx, "");
				}
			}
		}

		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			log.info(cause.getMessage());
			ctx.channel().close();
		}

	}
}
