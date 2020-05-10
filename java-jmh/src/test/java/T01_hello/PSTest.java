package T01_hello;

import org.openjdk.jmh.annotations.*;

public class PSTest {

    @Benchmark
    @Warmup(iterations = 1, time = 3) //预热， 执行一次， 等3秒钟
    @Fork(5)//指定使用多少线程进行测试和
    @BenchmarkMode(Mode.Throughput)//吞吐量的测试
    @Measurement(iterations = 1, time = 3)// 测试的次数, 1次,
    public void testForEach() {
        PS.foreach();
    }

}
