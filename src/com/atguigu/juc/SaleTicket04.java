package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail()throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("*****sendEmail");
    }
    public  synchronized void sendSMS()throws Exception{
        System.out.println("*****sendSMS");
    }
    public void hello(){
        System.out.println("*****hello");
    }
}




public class SaleTicket04 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(()->{
            try{
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(()->{
            try{
                //phone.sendSMS();
               // phone.hello();
                phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B").start();

    }
}
