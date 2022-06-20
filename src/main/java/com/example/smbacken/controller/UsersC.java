package com.example.smbacken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.javabean.Users;
import com.example.smbacken.service.AuthCodeService;
import com.example.smbacken.service.UsersService;
import com.example.smbacken.util.Json;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Validated
@RestController
@CrossOrigin
@RequestMapping("uc") // AuthCodeC
public class UsersC {
    @Autowired
    private UsersService usersService;
    @Autowired(required = false)
    private Json json;

    public boolean isUserExist(String phone) {
        // isAuthExist()存在返回true，
        if (usersService.isUserExist(phone)) {
            return true;
        } else { // isAuthExist()存在返回false，
            return false;
        }
    }

    // 假设没有这个用户的请款下运行这个方法，将该用户的phone加入到user库中，待前端注册使用。
    public void insertUserPhone(String phone){
        Users user = new Users();
        user.setPhone(phone);
        usersService.addUserPhone(user);
    }
}
