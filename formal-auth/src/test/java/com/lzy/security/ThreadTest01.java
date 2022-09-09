package com.lzy.security;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: zhanyang
 * @Date: 2022/8/31 10:08
 */
public class ThreadTest01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> task1 =
                CompletableFuture.supplyAsync(() -> {
                    //自定义业务操作
                    try {
                        task01();
                        task02();
                        task03();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "successs";
                });
        CompletableFuture<String> task6 =
                CompletableFuture.supplyAsync(() -> {
                    try {
                        task04();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "successs";
                });
        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(task1, task6);
        System.out.println(task6.get());
        try {
            headerFuture.join();
        } catch (Exception ex) {
            //......
        }
        System.out.println("all done. ");
    }


    public static void task01() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("------任务01------");
    }

    public static void task02() throws InterruptedException {
        System.out.println("------任务02------");
    }

    public static void task03() throws InterruptedException {
        System.out.println("------任务03------");
    }

    public static void task04() throws InterruptedException {
        System.out.println("------任务04------");
    }
}
