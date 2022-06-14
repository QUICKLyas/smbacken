package com.example.smbacken.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.javabean.ArticlesList;
import com.example.smbacken.service.ArticlesListService;
import com.example.smbacken.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("ALC") // ArticlesListC
public class ArticlesListC {
    @Autowired
    private ArticlesListService articlesListService;
    @Autowired(required = false)
    private Json json;
    private String pageNow = "page";
    private String pageSize = "size";
    private String condition = "condition";
    // find sort by like
    @RequestMapping (value = "/FSortByL", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findSortByLike(HttpServletRequest request, HttpServletResponse response){
        String condition = "like";
        return findSortMain(request,condition);
    }
    //find sort by views
    @RequestMapping(value = "/FSortByV", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findSortByViews(HttpServletRequest request, HttpServletResponse response){
        String condition = "views";
        return findSortMain(request,condition);
    }
    //find sort by comments
    @RequestMapping(value = "/FSortByC", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findSortByComments(HttpServletRequest request, HttpServletResponse response){
        String condition = "comments";
        return findSortMain(request,condition);
    }
    // 联想搜索的http接口
    @RequestMapping (value = "/SElementByObj", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject SElementByObj(HttpServletRequest request, HttpServletResponse response){
        JSONObject jsonObject;
        List<Object> list;
        try {
            jsonObject = json.getReqBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list = articlesListService.searchElement(jsonObject.getString(condition));
        return json.createJson(list);
    }
    private JSONObject findSortMain(HttpServletRequest request,String condition){
        JSONObject jsonObject;
        List<ArticlesList> list;
        try {
            jsonObject = json.getReqBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (jsonObject == null) {
            list = articlesListService.findArticlesListSort(condition);
        } else {
            list = articlesListService.findArticlesListSort(
                    condition,
                    jsonObject.getIntValue(pageNow)-1,
                    jsonObject.getIntValue(pageSize)
            );
        }
        return json.createJson(list);
    }

}
