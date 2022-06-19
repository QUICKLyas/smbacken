package com.example.smbacken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.Articles;
import com.example.smbacken.service.ArticlesService;
import com.example.smbacken.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Validated
@RestController
@CrossOrigin
@RequestMapping("AC") // ArticlesC
public class ArticlesC {
    @Autowired
    private ArticlesService articlesService;
    @Autowired(required = false)
    private Json json;
    // 通过Id获取文章
    @CrossOrigin
    @RequestMapping (value = "/findAll", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findAll(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject;
        List<Articles> list = null;
//        try {
//            //获取body内容，并且以JSONObject保存
//            jsonObject = json.getReqBody(request);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        // 根据需要获取body（JSONObject）中内容
//        ObjectId id = new ObjectId(jsonObject.getString("id"));
//        // 返回数据查询结果
//        Articles articles = articlesService.findArticlesById(id);
        return json.createJson(list);
    }
    // 阅读量 ，得到需要的文章，想数据库中更新阅读量
    @CrossOrigin
    @RequestMapping(value = "/Views",method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public JSONObject increaseViewCount(HttpServletRequest request,HttpServletResponse responses) throws Exception{
        JSONObject jsonObject;
        responses = json.setRespBody(responses);
        try {
            //获取body内容，并且以JSONObject保存
            jsonObject = json.getReqBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ObjectId id = new ObjectId(jsonObject.getString("articleId"));
        jsonObject = articlesService.updateViews(id);
        return json.createJson(jsonObject);
    }

}
