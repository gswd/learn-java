package netty.s01;

import io.netty.util.NettyRuntime;

public class Test {
    public static void main(String[] args) {
        System.out.println("CPU count : " + NettyRuntime.availableProcessors());
    }
}
