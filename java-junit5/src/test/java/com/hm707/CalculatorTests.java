package com.hm707;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hm707.common.Fast;

@DisplayName("Calculator Test")
class CalculatorTests {
	private static final Logger log = LoggerFactory.getLogger(CalculatorTests.class);

	@BeforeAll
	static void initAll() {
		log.info("===> @BeforeAll initAll()");
	}

	@AfterAll
	static void tearDownAll() {
		log.info("===> @AfterAll tearDownAll()");
	}

	@BeforeEach
	void init() {
		log.info("");
		log.info("---> @BeforeEach init()");
	}

	@AfterEach
	void tearDown() {
		log.info("---> @AfterEach tearDown()");
		log.info("");
	}


	@Test
	void myFirstTest() {
		log.info("begin test method >> myFirstTest() ");
		assertEquals(2, 1 + 1);
	}


	@Test
	@Disabled("for demonstration purposes")
	@DisplayName("skippedTest  ╯°□°）╯")
	void skippedTest() {
		// not executed
		log.info("disable test method >> skippedTest()");
	}

	@Test
	@Fast
	void fastAnnotationTest() {
		log.info("combination annotation >> fastAnnotationTest()");
	}

	@Test
	@DisplayName("testWithDisplayNameContainingEmoji()  \uD83D\uDE31")
	void testWithDisplayNameContainingEmoji() {
	}
}
