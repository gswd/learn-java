package com.hm707.hello01;

/**
 * Created by LH on 2017/11/29.
 */
public interface MessageEngineMXBean {
	//结束程序
	void stop();

	//查看程序是否暂停
	boolean isPaused();

	//暂停程序或者继续程序
	void pause(boolean pause);

	//修改message
	void changeMessage(Message message);

	Message getMessage();
}
