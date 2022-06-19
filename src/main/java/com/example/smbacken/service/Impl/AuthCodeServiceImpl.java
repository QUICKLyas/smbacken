package com.example.smbacken.service.Impl;

import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.service.AuthCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("AuthCodeService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AuthCodeServiceImpl implements AuthCodeService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AuthCode> findAuthCodeAll() {
        return mongoTemplate.findAll(AuthCode.class);
    }

    @Override
    public void addAuthCode(AuthCode authCode) {
        mongoTemplate.findOne();
    }

    @Override
    public void isAuthExist(AuthCode authCode) {

    }

    @Override
    public AuthCode getCode(String phone) {
        return null;
    }
}
