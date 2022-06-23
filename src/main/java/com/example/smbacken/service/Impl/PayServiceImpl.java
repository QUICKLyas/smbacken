package com.example.smbacken.service.Impl;

import com.alipay.api.AlipayApiException;
import com.example.smbacken.javabean.AliBean;
import com.example.smbacken.service.PayService;
import com.example.smbacken.util.DateF;
import com.example.smbacken.util.alipay.AliPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PayServiceImpl implements PayService {
 
    @Autowired
    private AliPay aliPay;
 
    @Override
    public String aliPay(AliBean alipayBean) throws AlipayApiException {
        DateF.getTime("aliPay : "+ alipayBean.toString());
        return aliPay.pay(alipayBean);
    }
}