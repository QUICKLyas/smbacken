package com.example.smbacken.service;

import com.example.smbacken.javabean.AuthCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthCodeService {
    public List<AuthCode> findAuthCodeAll();

    public void addAuthCode(AuthCode authCode);
    public void isAuthExist(AuthCode authCode);
    public AuthCode getCode(String phone);
}
