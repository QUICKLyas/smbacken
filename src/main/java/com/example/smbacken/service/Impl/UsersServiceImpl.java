package com.example.smbacken.service.Impl;

import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.javabean.Users;
import com.example.smbacken.service.UsersService;
import com.example.smbacken.util.DateF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service("UsersService")
public class UsersServiceImpl implements UsersService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public boolean isUserExist(String phone) {
        Users user = findUser(phone);
        if (user != null) {
            DateF.getTime("isUserExist : " + "true");
            return true;
        } else {
            DateF.getTime("isUserExist : " + "false");
            return false;
        }
    }

    @Override
    public Users findUser(String phone) {
        Pattern pattern = Pattern.compile("^.*"+phone+".*$", Pattern.CASE_INSENSITIVE);
        // 设置查询条件
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("phone").regex(pattern));
        // 查询结果
        Users user = mongoTemplate.findOne(findQuery,Users.class);
        DateF.getTime("getAuthCode : " + user);
        return user;
    }

    @Override
    public void addUserPhone(Users user) {
        Object object = mongoTemplate.save(user,"user");
        DateF.getTime("addUserPhone : " + object);
    }
}
