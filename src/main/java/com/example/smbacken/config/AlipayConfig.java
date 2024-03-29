package com.example.smbacken.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component
public class AlipayConfig {

    /**
     * 商户appid
     */
    @Value("${alipay.appid}")
    public String APPID;

    /**
     * 私钥 pkcs8格式的
     */
    @Value("${alipay.privateKey}")
    public String RSA_PRIVATE_KEY;
    /**
     * 服务器异步通知页面路径
     * 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    @Value("${alipay.notifyUrl}")
    public String notify_url;

    /**
     * 页面跳转同步通知页面路径
     * 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，
     * 必须外网可以正常访问 商户可以自定义同步跳转地址
     */
    @Value("${alipay.returnUrl}")
    public String return_url;

    /**
     * 请求网关地址
     */
    @Value("${alipay.url}")
    public String URL;

    /**
     * 编码
     */
    @Value("${alipay.charset}")
    public String CHARSET;

    /**
     * 返回格式
     */
    @Value("${alipay.format}")
    public String FORMAT;

    /**
     * 支付宝公钥
     */
    @Value("${alipay.publicKey}")
    public String ALIPAY_PUBLIC_KEY;

    /**
     * 日志记录目录定义在 logFile 中
     */
    @Value("${alipay.logPath}")
    public String log_path;

    /**
     * RSA2
     */
    @Value("${alipay.signType}")
    public String SIGNTYPE;

}