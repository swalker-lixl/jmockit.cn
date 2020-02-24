package cn.jmockit.demos;


import java.util.Locale;

import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.HelloJMockit;
import mockit.Expectations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Copyright (c) jmockit.cn 
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//HelloJMockit类的测试类
public class HelloJMockitTest {

	private static final Logger log = LoggerFactory.getLogger(HelloJMockitTest.class);

	/**
	 * 测试场景：当前是在中国
	 */
	@Test
	public void testSayHelloAtChina() {
		mockSayHello();
		mockSayName();

		String resultHello = (new HelloJMockit()).sayHello();
		System.out.println(resultHello);
		// 断言说中文
		Assert.assertTrue("你好，JMockit!".equals(resultHello));

		String resultName = (new HelloJMockit()).sayName();
		System.out.println(resultName);
		Assert.assertTrue("你好，我是李喜龙".equals(resultName));
	}

	/**
	 * 测试场景：当前是在美国
	 */
	@Test
	public void testSayHelloAtUS() {
		// 假设当前位置是在美国
		new Expectations(Locale.class) {
			{
				Locale.getDefault();
				result = Locale.US;
			}
		};
		// 断言说英文
		Assert.assertTrue("Hello，JMockit!".equals((new HelloJMockit()).sayHello()));
	}


	public void mockSayHello() {

		// 假设当前位置是在中国
		new Expectations(Locale.class) {
			{
				Locale.getDefault();
				result = Locale.CHINA;
			}
		};
	}

	public void mockSayName() {
		new MockUp<HelloJMockit>() {
			@Mock
			// 向JMockit说名字 ,颠倒
			public String sayName(Locale locale) {
				// 统一说英文
				return "你好，我是李喜龙";
			}
		};

	}

}
