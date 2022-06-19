package com.example.smbacken.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

public class SendCodeUtils {
    //---------------------- 固定格式----------------
    //发送验证码的请求路径URL
    private static final String SERVER_URL = "https://api.netease.im/sms/sendcode.action";
    // TODO
    // 网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String APP_KEY = "2ceab4c8ded1e2dfb4b2a5a4b930b9d5";
    // TODO
    // 网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET = "71ebc1beb5f1";
    //随机数
    private static final String NONCE = "123456";
    // TODO
    // 短信模板ID
    private static final String TEMPLATEID = "19507839";
    // TODO
    // 手机号
    private static final String MOBILE = "1864283";
    //验证码长度，范围4～10，默认为4
    private static final String CODELEN = "6";
    /**
     * 通过电话获取验证
     * @param phone
     * @return
     */
    public static String getCode(String phone) {
        String code = "";
        try {
            code = SendCodeUtils.sendSms(phone);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 返回到controller层，并存储到对应的对象中
        return code;
    }

    /**
     *
     * @param phone
     */
    private static String sendSms(String phone) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
        nvps.add(new BasicNameValuePair("mobile", phone));
        nvps.add(new BasicNameValuePair("codeLen", CODELEN));

        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        //--------------------- 接受返回参数，判断---------------------
        // 验证码
        String obj = JSON.parseObject(result).getString("obj");
        //获取发送状态码
        String code = JSON.parseObject(result).getString("code");

        if (code.equals("200")) {
            // 发送成功
            // result {"code":200,"msg":"105","obj":"410626"}
            DateF.getTime(result + ": 验证码发送成功。");
        } else {
            // 发送失败
            DateF.getTime(result + ": 验证码发送失败");
        }
        return obj;
    }
        // 闪速码
//        Client client = new Client();
//        client.setAppId("hw_11199");     //开发者ID，在【设置】-【开发设置】中获取
//        client.setSecretKey("24fc7611fa5e593da3606fbe12a48c50");    //开发者密钥，在【设置】-【开发设置】中获取
//        client.setVersion("1.0");
//
//        /**
//         *   json格式可在 bejson.com 进行校验
//         */
//        String singnstr = "smbacken";
//        Client.Request request = new Client.Request();
//        request.setMethod("sms.message.send");
//        request.setBizContent("{\"mobile\":[\""+ phone +"\"],\"type\":0,\"template_id\":" +
//                "\"ST_2020101100000007\",\"sign\":\""
//                + singnstr +"\",\"send_time\":\""+ client.getTimestamp() +"\",\"params\":{\"code\":"+code+"}}");
//        // 这里是json字符串，send_time 为空时可以为null,
//        // params 为空时可以为null,短信签名填写审核后的签名本身，不需要填写签名id
//        System.out.println( client.execute(request) );
//    }

}
