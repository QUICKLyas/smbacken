package com.example.smbacken.controller;

import com.alipay.api.AlipayApiException;
import com.example.smbacken.javabean.AliBean;
import com.example.smbacken.service.PayService;
import com.example.smbacken.util.MongoClientUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("oc")
public class OrderC {

    @Autowired
    private PayService payService;

    @SneakyThrows
    @RequestMapping(value = "alipay",method = {RequestMethod.GET,RequestMethod.POST})
    // String outTradeNo, String subject, String totalAmount, String body
    public String alipay(){
        AliBean alipayBean = new AliBean();
        alipayBean.setOut_trade_no(MongoClientUtil.dateToObjectId());
        alipayBean.setSubject("支持作者");
        alipayBean.setTotal_amount("0.01");
        alipayBean.setBody("");
        return payService.aliPay(alipayBean);
    }
}