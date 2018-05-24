package com.makotojava.learn.hellojunit5.solution;

/*
 * Copyright 2017 Makoto Consulting Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit 5 (with JUnitPlatform.class)
 *
 */
//@Disabled
//@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
public class JUnit5AppTest {

	private static final Logger log = LoggerFactory.getLogger(JUnit5AppTest.class);

	private App classUnderTest;

	@BeforeAll
	public static void init() {
		// 被注解的（静态）方法将在测试开始前执行一次
		log.info("@BeforeAll: init()");
	}

	@AfterAll
	public static void done() {
		// 被注解的（静态）方法将在测试结束后执行一次
		log.info("@AfterAll: done()");
	}

	@BeforeEach
	public void setUp() throws Exception {
		log.info("@BeforeEach: setUp()");
		classUnderTest = new App();
	}

	@AfterEach
	public void tearDown() throws Exception {
		log.info("@AfterEach: tearDown()");
		classUnderTest = null;
	}

	@Test
	@DisplayName("Dummy test")
	void aTest() {
		log.info("As written, this test will always pass!");
		assertEquals(4, (2 + 2));
	}

	@Test
	@DisplayName("Dummy test")
	void dummyTest(){
		int expected = 4;
		int actual = 2 + 2;
		assertEquals(expected, actual, "INCONCEIVABLE");

		Object nullValue = null;
		assertFalse(nullValue != null);
		assertNull(nullValue);
		assertNotNull("A String", "INCONCEIVABLE");
		assertTrue(nullValue == null);

		//assertAll 它包含的所有断言都会执行，即使一个或多个断言失败也是如此。
		assertAll(
				"Assert All of these",
				() -> assertEquals(expected, actual, "INCONCEIVABLE!"),
				() -> assertFalse(nullValue != null),
				() -> assertNull(nullValue),
				() -> assertNotNull("A String", "INCONCEIVABLE!"),
				() -> assertTrue(nullValue == null));
	}

	@Test
	@Disabled
	@DisplayName("A disabled test")
	void testNotRun() {
		log.info("This test will not run (it is disabled, silly).");
	}

	@Test
	@DisplayName("When numbers are > 0")
	public void testAdd() {
		log.info("@Test: testAdd()");
		assertNotNull(classUnderTest);
		assertAll(
				() -> {
					//
					// Test #1
					long[] numbersToSum = {1, 2, 3, 4};
					long expectedSum = 10;
					long actualSum = classUnderTest.add(numbersToSum);
					assertEquals(expectedSum, actualSum);
				},
				() -> {
					//
					// Test #2
					long[] numbersToSum = new long[] {20, 934, 110};
					long expectedSum = 1064;
					long actualSum = classUnderTest.add(numbersToSum);
					assertEquals(expectedSum, actualSum);
				},
				() -> {
					//
					// Test #3
					long[] numbersToSum = new long[] {2, 4, 6};
					long expectedSum = 12;
					long actualSum = classUnderTest.add(numbersToSum);
					assertEquals(expectedSum, actualSum);
				});
	}

	@Nested
	@DisplayName("When numbers to add are < 0")
	class NegativeNumbersTest {

		private App classUnderTest;

		@BeforeEach
		public void setUp() throws Exception {
			classUnderTest = new App();
		}

		@AfterEach
		public void tearDown() throws Exception {
			classUnderTest = null;
		}

		@Test
		@DisplayName("Three tests with numbers < 0")
		public void testAdd() {
			assertNotNull(classUnderTest);
			assertAll(
					() -> {
						//
						// Test #1
						long[] numbersToSum = {-1, -2, -3, -4};
						long expectedSum = -10;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					},
					() -> {
						//
						// Test #2
						long[] numbersToSum = {-20, -934, -110};
						long expectedSum = -1064;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					},
					() -> {
						//
						// Test #3
						long[] numbersToSum = new long[] {-2, -4, -6};
						long expectedSum = -12;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					});
		}
	}

	@Nested
	@DisplayName("When 0 < numbers > 0")
	class PositiveAndNegativeNumbersTest {

		@Test
		@DisplayName("Three tests with both positive and negative numbers")
		public void testAdd() {
			assertNotNull(classUnderTest);
			assertAll(
					() -> {
						//
						// Test #1
						long[] numbersToSum = {-1, 2, -3, 4};
						long expectedSum = 2;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					},
					() -> {
						//
						// Test #2
						long[] numbersToSum = {-20, 934, -110};
						long expectedSum = 804;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					},
					() -> {
						//
						// Test #3
						long[] numbersToSum = new long[] {-2, -4, 6};
						long expectedSum = 0;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					});
		}

		/**
		 * 前置条件 (Assumption) 与断言类似，但assumeTrue条件必须为 true，否则跳过该测试. 不会导致构建失败.
		 *
		 */
		@Test
		@DisplayName("This test is only run on Fridays")
		public void testAdd_OnlyOnFriday() {
			assertNotNull(classUnderTest);
			LocalDateTime ldt = LocalDateTime.now();
			assumeTrue(ldt.getDayOfWeek().getValue() == 5, "Test skipped... it's not Friday!");
			//assumeTrue(ldt.getDayOfWeek().getValue() == 5);
			long[] operands = {1, 2, 3, 4, 5};
			long expectedSum = 15;
			long actualSum = classUnderTest.add(operands);
			assertEquals(expectedSum, actualSum);
		}

		/**
		 * 无论 assumingThat() 中的前置条件成立与否，都会执行 lambda 表达式后的所有代码
		 */
		@Test
		@DisplayName("This test is only run on Fridays (with lambda)")
		public void testAdd_OnlyOnFriday_WithLambda() {
			assertNotNull(classUnderTest);
			LocalDateTime ldt = LocalDateTime.now();
			assumingThat(ldt.getDayOfWeek().getValue() == 5,
					() -> {
						long[] operands = {1, 2, 3, 4, 5};
						long expectedSum = 15;
						long actualSum = classUnderTest.add(operands);
						assertEquals(expectedSum, actualSum);
					});

			System.out.println("&&&&&&&&& -> other assert.");
		}

	}

	@Nested
	@DisplayName("When using a single operand")
	class JUnit5AppSingleOperandTest {

		@Test
		@DisplayName("Numbers > 0")
		public void testAdd_NumbersGt0() {
			assertNotNull(classUnderTest);
			assertAll(
					() -> {
						long[] numbersToSum = {1};
						long expectedSum = 1;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					},
					() -> {
						long[] numbersToSum = {0};
						long expectedSum = 0;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					});
		}

		@Test
		@DisplayName("Numbers < 0")
		public void testAdd_NumbersLt0() {
			assertNotNull(classUnderTest);
			assertAll(
					() -> {
						long[] numbersToSum = {-1};
						long expectedSum = -1;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					},
					() -> {
						long[] numbersToSum = {-10};
						long expectedSum = -10;
						long actualSum = classUnderTest.add(numbersToSum);
						assertEquals(expectedSum, actualSum);
					});
		}
	}

	@Nested
	@DisplayName("When zero operands")
	class JUnit5AppZeroOperandsTest {

		@Test()
		@DisplayName("Empty argument")
		public void testAdd_ZeroOperands_EmptyArgument() {
			assertNotNull(classUnderTest);
			long[] numbersToSum = {};
			assertThrows(IllegalArgumentException.class, () -> classUnderTest.add(numbersToSum));
		}

		@Test
		@DisplayName("Null argument")
		public void testAdd_ZeroOperands_NullArgument() {
			assertNotNull(classUnderTest);
			long[] numbersToSum = null;
			Throwable expectedException = assertThrows(IllegalArgumentException.class,
					() -> classUnderTest.add(numbersToSum));
			assertEquals("Operands argument cannot be null", expectedException.getLocalizedMessage());
		}

	}

}
