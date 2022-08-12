package com.utils.common;/*
package com.ldy.common;

import com.project.diamond.Service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyThread implements Runnable{
    private int sum;
    private int new_sum;
    private boolean stopMe = true;
    public void stopMe() {
        stopMe = false;
    }
    @Autowired
    private UserTestService userTestService;

    @Override
    public void run()  {
        */
/*sum = userTestService.SelectCount();*//*

        sum = 1;
        webSocket wbs=new webSocket();
        while(stopMe){
            */
/*new_sum=userTestService.SelectCount();*//*

            new_sum=3;
            if(sum!=new_sum){
                System.out.println("change");
                sum=new_sum;
                wbs.onMessage(sum);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
*/
