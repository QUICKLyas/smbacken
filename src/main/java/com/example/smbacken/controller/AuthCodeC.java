package com.example.smbacken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.service.AuthCodeService;
import com.example.smbacken.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("ACC") // AuthCodeC
public class AuthCodeC {
    @Autowired
    private AuthCodeService authCodeService;
    @Autowired(required = false)
    private Json json;
    @RequestMapping (value = "/findAll", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findAll(HttpServletRequest request, HttpServletResponse response){
        List<AuthCode> list;
        list = authCodeService.findAuthCodeAll();
        return json.createJson(list);
    }
}
