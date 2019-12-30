package com.atguigu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SaleTicket10 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟有3个车位

        for(int i= 1;i<=6;i++){//模拟6个汽车占车位
              new Thread(()->{
                  boolean flag = false;//没有抢到车位
                  try {
                      semaphore.acquire();
                      flag = true;//抢到车位
                      System.out.println(Thread.currentThread().getName()+"\t 抢到车位");
                      //暂停几秒钟线程
                      try{ TimeUnit.SECONDS.sleep(3);}catch(InterruptedException e){ e.printStackTrace();}
                      System.out.println(Thread.currentThread().getName()+"\t 离开车位");

                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      if(flag=true){
                          semaphore.release();
                      }
                  }
              },String.valueOf(i)).start();
        }
    }
}
