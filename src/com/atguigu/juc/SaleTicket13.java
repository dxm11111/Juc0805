package com.atguigu.juc;

import java.util.concurrent.*;

public class SaleTicket13 {


    public static void main(String[] args) {
        //一个银行已经new好了5个受理窗口，有5个工作人员
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5线程
      // ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1线程
       // ExecutorService threadPool = Executors.newCachedThreadPool();//一池N线程
       //自定义线程池
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy());默认
                //new ThreadPoolExecutor.CallerRunsPolicy()
                //new ThreadPoolExecutor.DiscardOldestPolicy()
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );


        try{
            for(int i=1;i<=8;i++){
                final int tempI = i;
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 受理业务"+"\t 客户号："+tempI);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
