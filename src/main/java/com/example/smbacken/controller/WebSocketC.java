package com.example.smbacken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.service.ArticlesListService;
import com.example.smbacken.util.Json;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;
@Slf4j
@Validated
@RestController
@ServerEndpoint("/WebSocket")
public class WebSocketC {
    @Autowired
    private ArticlesListService articlesListService;
    // 静态变量，用来记录当前在线连接数，应该设计成线程安全。
    private static int onlineCount = 0;
    // concurrent包的西安测绘给你安全Set，用来存放每个客户端对应的WebSocketC对象。
    private static CopyOnWriteArraySet<WebSocketC> webSocketSet = new CopyOnWriteArraySet<WebSocketC>();
    // 某个客户端的连接绘画，需要通过它来给客户端发送数据
    private Session session ;
    private static ConfigurableApplicationContext applicationContext;
    // 将启动类的SpringApplicationContext引入
    public static void setApplicationContext(ConfigurableApplicationContext applicationContext){
        WebSocketC.applicationContext = applicationContext;
    }
    @OnOpen
    public void onOpw(Session session){
        articlesListService = applicationContext.getBean(ArticlesListService.class);
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为："+getOnlineCount());
    }
    @SneakyThrows
    @OnMessage//收到消息执行
    public void onMessage(String condition,Session session){
        JSONObject jsonObject = JSONObject.parseObject(condition);
        sendMessage(String.valueOf(getList(jsonObject.getString("condition"))));
    }
    @OnClose//关闭连接执行
    public void onClose(Session session) {
        subOnlineCount();
        webSocketSet.remove(this);
        System.out.println("有连接断开！当前在线人数为："+getOnlineCount());
        System.out.println("关闭连接");
    }
    @OnError//连接错误的时候执行
    public void onError(Throwable error,Session session) {
        System.out.println("错误的时候执行");
        error.printStackTrace();
    }
    /*
    websocket  session发送文本消息有两个方法：getAsyncRemote()和
   getBasicRemote()  getAsyncRemote()和getBasicRemote()是异步与同步的区别，
   大部分情况下，推荐使用getAsyncRemote()。
  */
    public void sendMessage(String message) throws IOException {
        Json json = new Json();
        String response = message.substring(2,message.length()-2);
        String str[] = response.split(",");
        List<String> list = Arrays.asList(str);
        session.getBasicRemote().sendText(String.valueOf(json.createJson(list)));
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }
    public static synchronized void addOnlineCount(){
        WebSocketC.onlineCount++;
    }
    public static synchronized void subOnlineCount(){
        WebSocketC.onlineCount--;
    }
    private List<Object> getList(String condition){
         return articlesListService.searchElement(condition);
    }

}
