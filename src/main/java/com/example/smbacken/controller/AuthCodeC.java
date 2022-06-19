package com.example.smbacken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.service.AuthCodeService;
import com.example.smbacken.util.Json;
import com.example.smbacken.util.PhoneUtils;
import com.example.smbacken.util.SendCodeUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Validated
@RestController
@CrossOrigin
@RequestMapping("acc") // AuthCodeC
public class AuthCodeC {
    @Autowired
    private AuthCodeService authCodeService;
    @Autowired(required = false)
    private Json json;
    @SneakyThrows
    @RequestMapping (value = "/gmsgcode", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject getMessageCode(@RequestBody AuthCode authCode, HttpServletRequest request, HttpServletResponse response){
        // 设置response
        response = json.setRespBody(response);

        // 设置返回信息的缓存变量
        int errno = 200;
        String errmsg = "验证码获取成功";
        if(authCode.getPhone() == null ||  authCode.getPhone().equals("")) {
            errno = 3001;
            errmsg = "请输入手机号";
        }
        // 这里是工具类里面的方法，判断电话号码是否符合
        if(!PhoneUtils.isValidPhone(authCode.getPhone())) {
            errno = 3002;
            errmsg = "请输入正确格式手机号";
        }
        // 这个是获取验证码主体代码，获取验证码
        String phoneCode = SendCodeUtils.getCode(authCode.getPhone());
        if("".equals(phoneCode)){
            errno = 3003;
            errmsg = "获取验证码失败，请重试";
        }
        // 将验证码和手机号存到数据库
        authCode.setCode(phoneCode);
        // 连接service层,将数据添加到数据库中
        authCodeService.addAuthCode(authCode);
        return json.createJson(null,errmsg,errno);
    }
    @RequestMapping (value = "/findAll", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findAll(HttpServletRequest request, HttpServletResponse response){
        List<AuthCode> list;
        list = authCodeService.findAuthCodeAll();
        return json.createJson(list);
    }
}
