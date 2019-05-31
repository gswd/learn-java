package com.hm707.io.socket.nio.channel;

/**
 * Scatter/gather 又叫做矢量IO 它能一次读写多个多个buffer.
 *
 * scatter(分散): 实现了ScatteringByteChannel接口的channel都具有该特性,可以 scatter read
 * gather(汇聚): 实现了GatheringByteChannel接口的channel都具有该特性，可以 gather write
 *
 * 使用该特性时最好使用 direct buffer 现代操作系统都支持这一特性，IO时效率较高
 */
public class VectoredIO {


}