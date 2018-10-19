package com.hm707.beans.introspector;

public class BeanInfoTest {
	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("jerry");

		try {
			BeanInfoUtil.setProperty(userInfo, "userName");

			BeanInfoUtil.getProperty(userInfo, "userName");

			BeanInfoUtil.setPropertyByIntrospector(userInfo, "userName");

			BeanInfoUtil.getPropertyByIntrospector(userInfo, "userName");

			BeanInfoUtil.setProperty(userInfo, "age");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
