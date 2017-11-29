package com.hm707.hello01;

import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.NotificationListener;

/**
 * Created by LH on 2017/11/29.
 */
public class MessageEngine extends NotificationBroadcasterSupport implements MessageEngineMXBean {
	private long sequenceNumber = 1;
	private final Message message = Echo.msg;

	public MessageEngine() {
		addNotificationListener(new NotificationListener() {
			@Override
			public void handleNotification(Notification notification, Object handback) {
				System.out.println("*** Handling new notification ***");
				System.out.println("Message: " + notification.getMessage());
				System.out.println("Seq: " + notification.getSequenceNumber());
				System.out.println("*********************************");
			}
		}, null, null);
	}

	@Override
	public void stop() {
		Echo.running = false;
	}

	@Override
	public boolean isPaused() {
		return Echo.pause;
	}

	@Override
	public void pause(boolean pause) {
		Notification notification = new AttributeChangeNotification(this,
			sequenceNumber++, System.currentTimeMillis(),
			"Pause changed", "Paused", "boolean",
			Echo.pause, pause);
		Echo.pause = pause;
		this.sendNotification(notification);
	}

	@Override
	public void changeMessage(Message message) {
		Echo.msg = message;
	}

	@Override
	public Message getMessage() {
		return this.message;
	}

	// 规定可以发送的Notification Type，不在Type list中的Notification不会被发送。
	@Override
	public MBeanNotificationInfo[] getNotificationInfo() {
		String[] types = new String[] {
			AttributeChangeNotification.ATTRIBUTE_CHANGE
		};
		String name = AttributeChangeNotification.class.getName();
		String description = "An attribute of this MBean has changed";
		MBeanNotificationInfo info =
			new MBeanNotificationInfo(types, name, description);

		return new MBeanNotificationInfo[] {info};
	}
}
