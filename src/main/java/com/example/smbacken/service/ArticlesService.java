package com.example.smbacken.service;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.Articles;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticlesService {

    public List<Articles> findArticles();
    public Articles findArticlesById(ObjectId id);

    public JSONObject updateViews(ObjectId id);
}
