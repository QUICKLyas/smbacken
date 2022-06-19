package com.example.smbacken.util;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class Json {
    public static JSONObject createJson(Object obj){
        JSONObject jsonContainer = new JSONObject();
        jsonContainer.put("data", obj);
        jsonContainer.put("errmsg","success");
        jsonContainer.put("errno",200);
        return jsonContainer;
    }

    public static JSONObject createJson(Object obj,String errmsg,int errno){
        JSONObject jsonContainer = new JSONObject();
        jsonContainer.put("data", obj);
        jsonContainer.put("errmsg",errmsg);
        jsonContainer.put("errno",errno);
        return jsonContainer;
    }
    public static JSONObject getReqBody(HttpServletRequest request) throws IOException {
        // 直接从HttpServletRequest的Reader流中获取RequestBody
        BufferedReader reader = request.getReader();
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            line = reader.readLine();
        }
        reader.close();

        // request body 中的内容
        String reqBody = builder.toString();

        // 输出body传入的接口
        JSONObject json = JSONObject.parseObject(reqBody);

        System.out.println("recv ntydel from:" + request.getRequestURI());
        System.out.println("recv json data:" + json);
        // return
        return json;
    }
    public static HttpServletResponse setRespBody(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        // response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,HEAD,PUT,PATCH");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept,Authorization,authorization");
        return response;
    }
}
