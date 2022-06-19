package com.example.smbacken.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.Articles;
import com.example.smbacken.service.ArticlesService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("ArticlesService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Articles> findArticles() {
        return null;
    }

    @Override
    public Articles findArticlesById(ObjectId id) {
        return mongoTemplate.findById(id,Articles.class);
    }
    @Override
    public JSONObject updateViews(ObjectId id) {
        // 设置更新需要查找范围
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        // 获取原有数据
        query.fields().include("views");
        List<Articles> list = mongoTemplate.find(query,Articles.class);
        // 设置更新条目
        Update update = new Update();
        update.set("views", list.get(0).getViews()+1);
        mongoTemplate.upsert(query,update,Articles.class);
        list = mongoTemplate.find(query,Articles.class);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("views",list.stream().map(Articles::getViews).collect(Collectors.toList()).get(0));
        System.out.println(jsonObject);
        return jsonObject;
    }
}
