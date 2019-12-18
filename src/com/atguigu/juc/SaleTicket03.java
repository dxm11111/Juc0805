package com.atguigu.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class AirConditioner{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment() throws InterruptedException {
       lock.lock();

        try{
            while(number ==0){

                condition.await();
            }
            //干活
            --number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void decrement() throws InterruptedException {
        lock.lock();
        try{
            while(number !=0){

                condition.await();
            }
            //干活
            ++number;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
//class AirConditioner{
//    private int number = 0;
//    private Lock lock = new ReentrantLock();
//    public  void increment() throws InterruptedException {
//        //判断
//        while(number !=0){
//            this.wait();
//        }
//        //干活
//        ++number;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //通知
//        this.notifyAll();
//    }
//    public void decrement() throws InterruptedException {
//        //判断
//        while(number ==0) {
//            this.wait();
//        }
//        //干活
//        --number;
//        System.out.println(Thread.currentThread().getName()+"\t"+number);
//        //通知
//        this.notifyAll();
//    }
//}


/*
* 题目：现在有两个线程，可以操作初始值为零的一个变量，
* 实现一个线程对该变量加1，一个线程对该变量减1.
* 实现交替，来10轮，变量初始值为零。
* 1.高内聚低偶合前提下，线程操作资源类
* */


public class SaleTicket03 {
    public static void main(String[] args) {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(()->{
            for (int i = 1;i<=10;i++){
                try {
                    Thread.sleep(200);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1;i<=10;i++){
                try {
                    Thread.sleep(300);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1;i<=10;i++){
                try {
                    Thread.sleep(400);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(()->{
            for (int i = 1;i<=10;i++){
                try {
                    Thread.sleep(500);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}