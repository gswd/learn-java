package com.hm707.scanclass;

/**
 * Created with LXD
 *
 * @author:Luhui
 * @qq:729913162
 * @Date:2018-08-14
 * @Time:9:35
 */
public class ScannerClassException extends RuntimeException {

    public ScannerClassException(String message){
        super(message);
    }

    public ScannerClassException(String message,Throwable t){
        super(message,t);
    }



}
