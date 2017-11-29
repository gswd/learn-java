package com.hm707.hello02;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Created by LH on 2017/11/29.
 */
public class HelloAgent {
	public static void main(String[] args) throws Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		/**
		 * ObjectName中的取名是有一定规范的
		 * `域名:name=MBean名称`
		 * `域名`和`MBean的名称`可以任意取
		 * 这样定义后，就可以唯一标识我们定义的这个MBean的实现类了。
		 */
		ObjectName helloName = new ObjectName("jmxBean:name=hello");
		//create mbean and register mbean
		server.registerMBean(new Hello(), helloName);
		TimeUnit.MINUTES.sleep(60);

	}
}
