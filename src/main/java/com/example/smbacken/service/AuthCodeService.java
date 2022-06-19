package com.example.smbacken.service;

import com.example.smbacken.javabean.AuthCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthCodeService {
    public List<AuthCode> findAuthCodeAll();

    // 增加一条文档
    public void addAuthCode(AuthCode authCode);
    // 根据电话号码判断是否存在这个文档
    public boolean isAuthExist(String phone);
    // 根据phone的值的获取文档
    public List<String> getAuthCode(String phone);
    // 根据phone的值删除文档
    public void deleteAuthCode(String phone);
}
