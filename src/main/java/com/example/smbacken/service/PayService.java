package com.example.smbacken.service;

import com.alipay.api.AlipayApiException;
import com.example.smbacken.javabean.AliBean;

public interface PayService {
 
    String aliPay(AliBean alipayBean) throws AlipayApiException;
 
}