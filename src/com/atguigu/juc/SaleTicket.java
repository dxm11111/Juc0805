package com.atguigu.juc;

/*
题目：三个售票员     卖出     30张票
目的：如何写出企业级的多线程程序
*/
class Ticket{
    private  int number = 30;
    public synchronized void sale(){
        if(number>0){
            System.out.println(Thread.currentThread().getName()+"\t卖出"+(number--)+"\t剩余"+number);
        }
    }
}

public class SaleTicket extends Thread{
    public static void main(String[] args) {
            Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=30;i++){
                    ticket.sale();
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=30;i++){
                    ticket.sale();
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=30;i++){
                    ticket.sale();
                }
            }
        },"C").start();


    }


}
