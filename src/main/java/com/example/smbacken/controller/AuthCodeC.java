package com.example.smbacken.controller;

import com.alibaba.druid.util.DaemonThreadFactory;
import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.service.AuthCodeService;
import com.example.smbacken.util.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UsersC usersC;
    @SneakyThrows
    @RequestMapping (value = "/g-msg-code", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject getMessageCode(@RequestBody AuthCode authCode, HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map = new HashMap<String, Object>();
        // 设置response
        response = json.setRespBody(response);
        // 设置返回信息的缓存变量
        int errno = 200;
        String errmsg = "验证码获取成功";

        // 首先判断是否存在这个用户 ，存在就继续，不存在我们返回前端信息，并等待下次的请求
        if(usersC.isUserExist(authCode.getPhone())){ // 表示用户存在，不需要注册 needRegister: false
            map.put("needRegister", false);
            map.put("success",true);
        } else { // 表示用户不存在，需要注册 needRegister: true
            DateF.getTime("needRegister: true");
            usersC.insertUserPhone(authCode.getPhone());
            errno = 2001;
            authCode.setCode(SendCodeUtils.getCode(authCode.getPhone()));
//            System.out.println(authCode.getCode());
            errmsg = "需要注册";
            map.put("needRegister",true);
            map.put("success",true);
            authCodeService.addAuthCode(authCode);
            return json.createJson(map,errmsg,errno);
        }
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

//        authCode.setCode("123456");
        if("".equals(authCode.getCode())){
            errno = 3003;
            errmsg = "获取验证码失败，请重试";

        }
        // 连接service层,将数据添加到数据库中
        authCodeService.addAuthCode(authCode);
        return json.createJson(map,errmsg,errno);
    }

    @RequestMapping(value = "/image-code", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject getImageCode(@RequestBody AuthCode authCode, HttpServletRequest request, HttpServletResponse response){
        Object[] objects = VerifyUtil.createImage();
        String randomStr = (String) objects[0];
//        log.info("randomStr result:" + objects.toString());
        // 将这个code存储到数据库中，做临时的存储

        authCodeService.updateAuthImageCode(authCode.getPhone(),objects[0].toString());
//        System.out.println(objects[0].toString());
        return json.createJson(objects[1].toString().replaceAll("\r|\n",""));

    }

    @RequestMapping(value="/login-confirm", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject loginConfirm(@RequestBody AuthCode authCode, HttpServletRequest request, HttpServletResponse response){
        // 判断这个字段是否已经传入
        if (authCode.getPhone() == null ){
            return json.createJson(0,"请输入手机号",2001);
        }
        // 判断手机号是否存在AuthCode
        if (!authCodeService.isAuthExist(authCode.getPhone())){
            return json.createJson(0,"手机号不存在",2001);
        }
        // 获取两个字符串
        List<String> listImageCode = authCodeService.getAuthImageCode(authCode.getPhone());
        List<String> listCode= authCodeService.getAuthCode(authCode.getPhone());
        // 手机号存在，判断手机验证吗是否传入
        if (authCode.getCode() == null ) {
            return json.createJson(0,"请输入手机验证码",2001);
        } else { // 有传入对应的手机验证码，判断是否有图形验证码，有就判断两个验证码，
            if (listImageCode.contains(authCode.getImageCode()) && listCode.contains(authCode.getCode())) { // 没有图形验证码，单独判断手机验证码
                    authCodeService.deleteAuthCode(authCode.getPhone());
                    return json.createJson(1);
            } else {
                return json.createJson(0,"请重新输入验证码",2001);
            }
        }
    }
    @RequestMapping (value = "/find-all", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findAll(HttpServletRequest request, HttpServletResponse response){
        List<AuthCode> list;
        list = authCodeService.findAuthCodeAll();
        return json.createJson(list);
    }
}
