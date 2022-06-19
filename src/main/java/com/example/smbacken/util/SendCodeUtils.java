package com.example.smbacken.util;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class SendCodeUtils {
    //查账户信息的http地址
//    private static String URI_GET_USER_INFO =
//            "https://sms.yunpian.com/v2/user/get.json";
//    //智能匹配模板发送接口的http地址
//    private static String URI_SEND_SMS =
//            "https://sms.yunpian.com/v2/sms/single_send.json";
//    //模板发送接口的http地址
//    private static String URI_TPL_SEND_SMS =
//            "https://sms.yunpian.com/v2/sms/tpl_single_send.json";
//    //发送语音验证码接口的http地址
//    private static String URI_SEND_VOICE =
//            "https://voice.yunpian.com/v2/voice/send.json";
//    //绑定主叫、被叫关系的接口http地址
//    private static String URI_SEND_BIND =
//            "https://call.yunpian.com/v2/call/bind.json";
//    //解绑主叫、被叫关系的接口http地址
//    private static String URI_SEND_UNBIND =
//            "https://call.yunpian.com/v2/call/unbind.json";
//    //编码格式。发送编码格式统一用UTF-8
//    private static String ENCODING = "UTF-8";
//    //修改为您的apikey.apikey可在官网（http://www.yunpian.com)登录后获取
//    private static String apikey = "1400694354";
//    //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
//    long tpl_id = 2937596;
    // 设置一下发送的编码的格式

    private static int appId = 1400694354;
    private static String apikey = "80cf03b212fb2e7d340641184aa3685b";
    private static int templateId = 1445638;
    private static String smsSign = "smbacken.0";
    private static String str = "0123456789";

    private static int capacity = 6;
    /**
     * 通过电话获取验证
     * @param phone
     * @return
     */
    public static String getCode(String phone) {
        StringBuilder stringBuilder = new StringBuilder(capacity);
        // 随机生成一个验证码，长度默认设置为 acpacuty = 6
        for (int i = 0 ; i < capacity ; i++ ){
            char ch = str.charAt(new Random().nextInt(str.length()));
            stringBuilder.append(ch);
        }
        // 查账户信息调用示例
        // 使用智能匹配模板接口发短信(推荐)
        // 设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        // 发送短信的实例
        SendCodeUtils.sendSms(stringBuilder.toString(),phone);
        // 返回到controller层，并存储到对应的对象中
        String code = stringBuilder.toString();
        return code;
    }

    /**
     *
     * @param text
     * @param phone
     */
    private static void sendSms(String text, String phone) {
        String[] params = {text};
        try {
            SmsSingleSender sendMessage = new SmsSingleSender(appId, apikey);
            SmsSingleSenderResult result = sendMessage.sendWithParam("86",phone,templateId,
                    params,smsSign,"","");
            System.out.println(result);
        } catch (HTTPException | JSONException e) {
            System.err.println("HttpException | JSONException e");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IOException");
            e.printStackTrace();
        }
    }

}
