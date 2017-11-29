package com.hm707.hello02;

/**
 * Created by LH on 2017/11/29.
 */
interface HelloMBean {

	String getName();

	void setName(String name);

	String getAge();

	void setAge(String age);

	void helloWorld();

	void helloWorld(String str);

	void getTelephone();
}
