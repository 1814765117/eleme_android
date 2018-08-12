package com.elemexyh.clientUtil;

public interface HttpCallbackListener {
	void onFinish(String response);
	
	void onError(Exception e);
}
