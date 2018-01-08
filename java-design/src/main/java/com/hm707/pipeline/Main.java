package com.hm707.pipeline;

public class Main {
	public static void main(String[] args){
        MyPipeline pipeline=new MyPipeline();  
        pipeline.addFirst(new TestHandler2());
        pipeline.addFirst(new TestHandler1());
        for(int i=0;i<10;i++){//提交多个任务  
            pipeline.Request("hello"+i);  
        }  
    }  
} 