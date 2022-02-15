package com.hm707.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class ConcurrentHashMapTest {

	public static void main(String[] args) throws InterruptedException {
		int threadCount = 8;

		ForkJoinPool forkJoinPool = new ForkJoinPool(threadCount);

		forkJoinPool.execute(() -> IntStream.range(0, threadCount)
			.mapToObj(i -> new User("张三", i))
			.parallel().forEach(UserService::register1));


		// 等待1s，否则看不到日志输出程序就结束了
		TimeUnit.SECONDS.sleep(3);
		System.out.println("======");
		UserService.printUserMap();

	}

	static class UserService {
		private static Map<String, User> userMap = new ConcurrentHashMap<>();

		static boolean register(User user) {
			if (userMap.containsKey(user.getUserName())) {
				System.out.println("用户已存在");
				return false;
			} else {

				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				userMap.put(user.getUserName(), user);
				System.out.println("用户注册成功," + user.getUserName() + "  " +  user.getAge());
				return true;
			}
		}
		static boolean register1(User user) {
			User user1 = userMap.putIfAbsent(user.getUserName(), user);
			if (user1 != null) {
				System.out.println("用户已存在");
				return false;
			} else {

				try {
					TimeUnit.MILLISECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//				userMap.put(user.getUserName(), user);
				System.out.println("用户注册成功," + user.getUserName() + "  " +  user.getAge());
				return true;
			}
		}

		public static void printUserMap() {
			userMap.forEach((k, v) -> {
				System.out.println(k + " : " + v);
			});
		}
	}


	@Getter
	@Setter
	@AllArgsConstructor
	@ToString
	static class User {
		private String userName;
		private int age;

	}
}
