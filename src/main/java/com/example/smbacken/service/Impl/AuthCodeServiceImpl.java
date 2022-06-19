package com.example.smbacken.service.Impl;

import com.example.smbacken.javabean.ArticlesList;
import com.example.smbacken.javabean.AuthCode;
import com.example.smbacken.service.AuthCodeService;
import com.example.smbacken.util.DateF;
import com.example.smbacken.util.trie.TrieTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
    public boolean isAuthExist(String phone) {
        List<String> list = getAuthCode(phone);
        if (list.size() != 0) {
            DateF.getTime("isAuthExist : " + "true");
            return true;
        } else {
            DateF.getTime("isAuthExist : " + "false");
            return false;
        }
    }

    @Override
    public List<String> getAuthCode(String phone) {
        Pattern pattern = Pattern.compile("^.*"+phone+".*$", Pattern.CASE_INSENSITIVE);
        // 设置查询条件
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("phone").regex(pattern));
        findQuery.fields().exclude("_id");
        findQuery.fields().include("code");
        // 查询结果
        List<AuthCode> authCodes = mongoTemplate.find(findQuery,AuthCode.class);

        // 截取结果中特定的字段
        List<String> codes = authCodes.stream().map(AuthCode::getCode).collect(Collectors.toList());
        DateF.getTime("getAuthCode : " + codes);
        return codes;
    }

    @Override
    public void deleteAuthCode(String phone) {
        Query query = Query.query(Criteria.where("phone").is(phone));
        Object object = mongoTemplate.remove(query, AuthCode.class);
        DateF.getTime("deleteAuthCode : " + object);
    }
}
