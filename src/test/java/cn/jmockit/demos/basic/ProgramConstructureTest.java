package cn.jmockit.demos.basic;

import org.junit.Assert;
import org.junit.Test;

import cn.jmockit.demos.HelloJMockit;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;

/*
 * Copyright (c) jmockit.cn
 * 访问JMockit中文网(jmockit.cn)了解该测试程序的细节
 */
//JMockit的程序结构
public class ProgramConstructureTest {

    // 这是一个测试属性
    @Mocked
    private HelloJMockit helloJMockit;

    @Test
    public void testAttribute() {
        // 录制(Record)
        new Expectations() {
            {
                helloJMockit.sayHello();
                // 期待上述调用的返回是"hello,david"，而不是返回"hello,JMockit"
                result = "hello,david";
            }
        };
        // 重放(Replay)
        String msg = helloJMockit.sayHello();
        Assert.assertEquals("hello,david", msg);
        // 验证(Verification)
        new Verifications() {
            {
                helloJMockit.sayHello();

                times = 1;
            }
        };
    }

    @Test
    public void testParameter(@Mocked HelloJMockit helloJMockit /* 这是一个测试参数 */) {
        // 录制(Record)
        new Expectations() {
            {
                helloJMockit.sayHello();
                // 期待上述调用的返回是"hello,david"，而不是返回"hello,JMockit"
                result = "hello,david";
            }
        };
        // 重放(Replay)
        String msg = helloJMockit.sayHello();
        Assert.assertEquals("hello,david", msg);
        // 验证(Verification)
        new Verifications() {
            {
                helloJMockit.sayHello();
                // 验证helloJMockit.sayHello()这个方法调用了1次
                times = 1;
            }
        };
    }
}
