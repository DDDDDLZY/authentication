package com.utils.common;/*
package com.ldy.common;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value="/websocket")
@Component
public class webSocket
{
    @Autowired
    protected UserTestService userTestService;
    private static webSocket websocket;

    @PostConstruct
    public void init() {
        websocket=this;
        websocket.userTestService =this.userTestService;
    }

    private int new_sum;
    private boolean stopMe = true;
    public void stopMe() {
        stopMe = false;
    }


    //用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<webSocket> webSocketSet = new CopyOnWriteArraySet<webSocket>();
    private Session session=null;

    */
/**
     * @ClassName: onOpen
     * @Description: 开启连接的操作
     *//*

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session=session;
        webSocketSet.add(this);
        System.out.println(webSocketSet);
        // todo 开启一个线程对数据库中的数据进行轮询
*/
/*        thread.start();*//*

        queryDb();

    }

    */
/**
     * @ClassName: onClose
     * @Description: 连接关闭的操作
     *//*

    @OnClose
    public void onClose(){
        stopMe();
        webSocketSet.remove(this);
    }

    */
/**
     * @ClassName: onMessage
     * @Description: 给服务器发送消息告知数据库发生变化
     *//*

    @OnMessage
    public void onMessage(int count) {
        System.out.println("发生变化"+count);
        try {
            sendMessage();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    */
/**
     * @ClassName: OnError
     * @Description: 出错的操作
     *//*

    @OnError
    public void onError(Throwable error){
        System.out.println(error);
        error.printStackTrace();
    }

    */
/**
     * @Description:发送消息方法
     * @throws IOException
     *//*

    public void sendMessage() throws IOException{
        //发消息
*/
/*        Thread thread=new Thread();*//*

        for(webSocket item: webSocketSet){
            int counts = websocket.userTestService.SelectCount();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("state","1");
            map.put("data",counts*125);//测试 让数大一些
            JSONObject jasonObject = JSONObject.fromObject(map);
            item.session.getBasicRemote().sendText(jasonObject.toString());
        }
    }

    public void queryDb()  {
        */
/*int sum = websocket.userTestService.SelectCount();*//*

        int sum = -1;
        System.out.println("sum="+sum);
        while(stopMe){
            new_sum=websocket.userTestService.SelectCount();
            if(sum!=new_sum){
                System.out.println("change");
                sum=new_sum;
                onMessage(sum);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}

*/
