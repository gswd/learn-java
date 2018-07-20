package com.hm707.beans.introspector;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanInfoUtil {

	public static void setProperty(UserInfo userInfo, String userName) throws Exception {
		PropertyDescriptor propDesc = new PropertyDescriptor(userName, UserInfo.class);
		Method methodSetUserName = propDesc.getWriteMethod();
		methodSetUserName.invoke(userInfo, "wong");
		System.out.println("set userName:" + userInfo.getUserName());
	}

	public static void getProperty(UserInfo userInfo, String userName) throws Exception {
		PropertyDescriptor proDescriptor = new PropertyDescriptor(userName, UserInfo.class);
		Method methodGetUserName = proDescriptor.getReadMethod();
		Object objUserName = methodGetUserName.invoke(userInfo);
		System.out.println("get userName:" + objUserName.toString());
	}

	public static void setPropertyByIntrospector(UserInfo userInfo, String userName) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
		PropertyDescriptor[] proDescriptors = beanInfo.getPropertyDescriptors();
		if (proDescriptors != null && proDescriptors.length > 0) {
			for (PropertyDescriptor propDesc : proDescriptors) {
				if (propDesc.getName().equals(userName)) {
					Method methodSetUserName = propDesc.getWriteMethod();
					methodSetUserName.invoke(userInfo, "alan");
					System.out.println("set userName:" + userInfo.getUserName());
					break;
				}
			}
		}
	}

	public static void getPropertyByIntrospector(UserInfo userInfo, String userName) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(UserInfo.class);
		PropertyDescriptor[] proDescrtptors = beanInfo.getPropertyDescriptors();
		if (proDescrtptors != null && proDescrtptors.length > 0) {
			for (PropertyDescriptor propDesc : proDescrtptors) {
				if (propDesc.getName().equals(userName)) {
					Method methodGetUserName = propDesc.getReadMethod();
					Object objUserName = methodGetUserName.invoke(userInfo);
					System.out.println("get userName:" + objUserName.toString());
					break;
				}
			}
		}
	}

}