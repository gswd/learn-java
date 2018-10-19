package com.hm707.beans.beanutils;

import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;

import com.hm707.beans.introspector.UserInfo;

public class BeanUtilsTest {
	public static void main(String[] args) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("jerry");

		try {
			BeanUtils.setProperty(userInfo, "userName", "tom");
			System.out.println(BeanUtils.getProperty(userInfo, "userName"));
			BeanUtils.setProperty(userInfo, "age", "10");
			System.out.println(BeanUtils.getProperty(userInfo, "age"));

			BeanUtils.setProperty(userInfo, "birthday", new Date(1000));
			Object obj = BeanUtils.getProperty(userInfo, "birthday");
			System.out.println(obj.getClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
