package com.example.smbacken.util.alipay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.example.smbacken.javabean.AliBean;
import org.springframework.stereotype.Component;

@Component
public class AliPay {
    /**
     * 支付接口
     *
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    public String pay(AliBean alipayBean) throws AlipayApiException {
        // 1、获得初始化的AlipayClient
        String serverUrl = AliPayProperties.getGatewayUrl();
        String appId = AliPayProperties.getAppId();
        String privateKey = AliPayProperties.getPrivateKey();
        String format = "json";
        String charset = AliPayProperties.getCharset();
        String alipayPublicKey = AliPayProperties.getPublicKey();
        String signType = AliPayProperties.getSignType();
        String returnUrl = AliPayProperties.getReturnUrl();
        String notifyUrl = AliPayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, appId, privateKey, format, charset, alipayPublicKey, signType);
        // 2、设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(returnUrl);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notifyUrl);
        // 封装参数
        alipayRequest.setBizContent(JSON.toJSONString(alipayBean));
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
//        System.out.println(result);
        // 返回付款信息
        return result;
    }
}