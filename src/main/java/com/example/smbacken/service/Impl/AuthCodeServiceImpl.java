package com.example.smbacken.service.Impl;

import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.service.AuthCodeService;
import com.example.smbacken.util.DateF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        Object object = mongoTemplate.save(authCode,"AuthCode");
        DateF.getTime("addAuthCode : " + object);
    }
    @Override
    public void updateAuthImageCode(String phone,String imageCode) {
        // 设置查询条件
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("phone").is(phone));
        Update update = new Update();
        update.set("imageCode", imageCode);
        Object object = mongoTemplate.upsert(findQuery,update, AuthCode.class);
        DateF.getTime("updateAuthImageCode : " + object + ", " + "phone :"+imageCode);
    }



    @Override
    public boolean isAuthExist(String phone) {
        List<String> list = getAuthCode(phone);
        if (list.size() != 0 ) {
            DateF.getTime("isAuthExist : " + list + " true");
            return true;
        } else {
            DateF.getTime("isAuthExist : " + list + " false");
            return false;
        }
    }
    @Override
    public boolean isAuthImageCodeExist(String phone, String imageCode) {
        List<String> list = getAuthImageCode(phone);
        if (list.size() != 0 ){
            DateF.getTime("isAuthImageCodeExist : " + list + " true");
            return true;
        } else {
            DateF.getTime("isAuthImageCodeExist : " + list + " false");
            return false;
        }
    }
    @Override
    public List<String> getAuthCode(String phone) {
        // 截取结果中特定的字段
        List<String> codes = getAuthC(phone).stream().map(AuthCode::getCode).collect(Collectors.toList());
        DateF.getTime("getAuthCode : " + codes);
        return codes;
    }
    @Override
    public List<String> getAuthImageCode(String phone) {
        List<String> imageCodes = getAuthC(phone).stream().map(AuthCode::getImageCode).collect(Collectors.toList());
        // 截取结果中特定的字段
        DateF.getTime("getAuthImageCode : " + imageCodes);
        return imageCodes;
    }

    @Override
    public void deleteAuthCode(String phone) {
        Query query = Query.query(Criteria.where("phone").is(phone));
        Object object = mongoTemplate.remove(query, AuthCode.class);
        DateF.getTime("deleteAuthCode : " + object);
    }
    public List<AuthCode> getAuthC(String phone) {
        // 设置查询条件
        Pattern pattern = Pattern.compile("^.*"+phone+".*$", Pattern.CASE_INSENSITIVE);
        // 设置查询条件
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("phone").regex(pattern));
        // 查询结果
        List<AuthCode> authCodes = mongoTemplate.find(findQuery,AuthCode.class);
        return authCodes;
    }
}
