package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

public class SaleTicket08 {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for(int i= 1;i<=6;i++){
             new Thread(()->{
                   System.out.println(Thread.currentThread().getName()+"\t离开教室");
                    countDownLatch.countDown();
                   },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t班长关门走人");
    }
}
