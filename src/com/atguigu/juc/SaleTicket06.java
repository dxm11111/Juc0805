package com.atguigu.juc;

//多线程之间按顺序调用，实现A先干->B次之->C最后

//三个线程启动要求如下：
//AA打印5次，BB打印10次，CC打印15次
//接着
//AA打印5次，BB打印10次，CC打印15次
//。。。。。来10轮

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//1.高聚低合前提下，线程操作资源类
//2.判断/干活/通知
//3.多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只用while,不能用if)
class ShareResource{
    private int flag = 1;//1:A  2:B  3:C
    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try{
            //1判断
            while(flag !=1) {
                //A系统停止
                c1.await();
            }
            //2 干活
            for (int i = 1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag = 2;
            c2.signal();


        } finally {
            lock.unlock();
        }


    }
    public void print10() throws InterruptedException {
        lock.lock();
        try{
            //1判断
            while(flag !=2) {
                //A系统停止
                c2.await();
            }
            //2 干活
            for (int i = 1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag = 3;
            c3.signal();

        } finally {
            lock.unlock();
        }


    }
    public void print15() throws InterruptedException {
        lock.lock();
        try{
            //1判断
            while(flag !=3) {
                //A系统停止
                c3.await();
            }
            //2 干活
            for (int i = 1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flag = 1;
            c1.signal();

        } finally {
            lock.unlock();
        }


    }
}


public class SaleTicket06 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for(int i = 1;i<=10;i++){
                try{
                    shareResource.print5();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for(int i = 1;i<=10;i++){
                try{
                    shareResource.print10();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for(int i = 1;i<=10;i++){
                try{
                    shareResource.print15();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}