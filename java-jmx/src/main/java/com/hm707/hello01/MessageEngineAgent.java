package com.hm707.hello01;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * Created by LH on 2017/11/29.
 */
public class MessageEngineAgent {
	public void start() {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		try {
			ObjectName mxbeanName = new ObjectName("com.hm707.hello01:type=MessageEngine");
			MessageEngineMXBean mxbean = new MessageEngine();
			try {
				mbs.registerMBean(mxbean, mxbeanName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
	}
}
