package com.atguigu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Ticket111{
    private  int number = 30;
    public synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出"+(number--)+"\t剩余"+number);
        }
    }

}

public class SaleTicket111 extends Thread{
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Ticket111 ticket = new Ticket111();

            try{
                for(int i=1;i<=30;i++){
                    threadPool.execute(()->{
                        ticket.sale();
                    });
                }
            }catch(Exception e){
                e.printStackTrace();

            }finally {
                threadPool.shutdown();
            }

    }
}
