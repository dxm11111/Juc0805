package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{ for(int i=1;i<=30;i++){ ticket.sale(); } },"A").start();
        new Thread(()->{ for(int i=1;i<=30;i++){ ticket.sale(); } },"B").start();
        new Thread(()->{ for(int i=1;i<=30;i++){ ticket.sale(); } },"C").start();


    }
}
class Ticket1{
    private Lock lock=new ReentrantLock();
    private int number = 30;
    public void sale(){
        lock.lock();
        try{
            if(number>0){
                System.out.println(Thread.currentThread().getName()+"\t卖出"+(number--)+"\t剩余"+number);
            }
        }catch(Exception e){
            e.printStackTrace();

        }finally {
            lock.unlock();
        }
    }
}