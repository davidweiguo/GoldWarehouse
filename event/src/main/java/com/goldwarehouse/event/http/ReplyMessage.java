package com.goldwarehouse.event.http;

public enum ReplyMessage {
	TOKEN_INVALID("Token is invalid"),
	WRONG_METHOD("Request method not support"),
	KEY_INVALID("Registry key invalid"),
	ORDER_ID_EMPTY("Order id is invalid");
	
	private String msg;
	ReplyMessage(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
}
