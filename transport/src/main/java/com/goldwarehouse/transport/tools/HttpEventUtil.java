package com.goldwarehouse.transport.tools;

import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class HttpEventUtil {
	public static boolean checkCipher(String create) {
		try {
			long valid = Long.parseLong(create);
			if (valid < System.currentTimeMillis()) {
				return false;
			}
			return true;
		} catch (Exception e) {
			return false;
		}		
	}
	
	public static void returnFail(ChannelHandlerContext ctx, String msg) {
		ByteBuf ret = Unpooled.wrappedBuffer(msg.getBytes());
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST, ret);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
		ctx.write(response);
		ctx.flush();
		ctx.close();
	}

	public static void returnTimeout(ChannelHandlerContext ctx) {
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
				HttpResponseStatus.INTERNAL_SERVER_ERROR);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
		ctx.write(response);
		ctx.flush();
		ctx.close();
	}

	public static void sendResponse(ChannelHandlerContext ctx, String msg) {
		ByteBuf ret = Unpooled.wrappedBuffer(msg.getBytes());
		FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, ret);
		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());
		ctx.write(response);
		ctx.flush();
		ctx.close();
	}
	
	public static <T> T stringToClass(Class<T> c, String data) {
		Gson gson = new Gson();
		return gson.fromJson(data, c);
	}
	
	public static <T> String classToString(T t) {
		Gson gson = new Gson();
		return gson.toJson(t);
	}
}
