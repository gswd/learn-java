package com.hm707;


import static java.time.Duration.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssertionDemo {
	private static final Logger log = LoggerFactory.getLogger(AssertionDemo.class);
	private static Person person = new Person();

	@BeforeAll
	static void initAll() {
		person.setFirstName("John");
		person.setLastName("Doe");
	}

	@Test
	void standardAssertions() {
		assertEquals(2, 2);
		assertEquals(4, 4, "The optional assertion message is now the last parameter.");
		assertTrue(true, () -> "Assertion messages can be lazily evaluated  to avoid constructing complex messages unnecessarily.");

	}

	@Test
	void groupedAssertions() {
		//所有的断言都会执行
		assertAll("test person name ",
				() -> assertEquals("John", person.getFirstName()),
				() -> assertEquals("Doe", person.getLastName())
		);
	}

	@Test
	void dependentAssertions() {
		// 所有代码块都会执行，代码块中的断言报错后会跳过后面的代码
		assertAll("properties",
				() -> {
					String firstName = person.getFirstName();
					assertNotNull(firstName);

					// Executed only if the previous assertion is valid.
					assertAll("first name",
							() -> assertTrue(firstName.startsWith("J")),
							() -> assertTrue(firstName.endsWith("n"))
					);
				},
				() -> {
					// Grouped assertion, so processed independently of results of first name assertions.
					String lastName = person.getLastName();
					assertNotNull(lastName);

					// Executed only if the previous assertion is valid.
					assertAll("last name",
							() -> assertTrue(lastName.startsWith("D")),
							() -> assertTrue(lastName.endsWith("e"))
					);
				}
		);
	}


	@Test
	void exceptionTesting() {
		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("a message");
		});
		assertEquals("a message", exception.getMessage());
	}

	@Test
	void timeoutNotExceeded() {
		assertTimeout(ofSeconds(2), () -> {
			// 执行时间小于两秒的任务
			Thread.sleep(1000);

		});
	}

	@Test
	void timeoutNotExceededWithResult() {
		// The following assertion succeeds, and returns the supplied object.
		String actualResult = assertTimeout(ofMinutes(2), () -> "a result");

		assertEquals("a result", actualResult);
	}

	@Test
	void timeoutNotExceededWithMethod() {
		// The following assertion invokes a method reference and returns an object.
		String actualGreeting = assertTimeout(ofMinutes(2), AssertionDemo::greeting);
		assertEquals("hello world!", actualGreeting);
	}

	@Test
	void timeoutExceeded() {
		// 方法块一定会执行完成，如果超时再抛出异常。
		// 下面断言会失败，显示类似下面的消息
		// execution exceeded timeout of 10 ms by 91 ms
		assertTimeout(ofSeconds(2), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(5000);
			log.info("assertTimeout() -> 执行完毕");
		});
	}
	@Test
	void timeoutExceededWithPreemptiveTermination() {
		// Preemptive : 抢占式的，一旦超时立即结束
		// The following assertion fails with an error message similar to:
		// execution timed out after 10 ms
		assertTimeoutPreemptively(ofMillis(10), () -> {
			// Simulate task that takes more than 10 ms.
			Thread.sleep(100);
			log.info("assertTimeoutPreemptively() -> 执行完毕");
		});
	}
	private static String greeting() {
		return "hello world!";
	}


}
