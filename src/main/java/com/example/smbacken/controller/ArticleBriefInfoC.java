package com.example.smbacken.controller;

import com.example.smbacken.javabean.ArticleBriefInfo;
import com.example.smbacken.service.ArticleBriefInfoService;
import com.alibaba.fastjson.JSONObject;
import com.example.smbacken.util.Json;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Validated
@RestController
@RequestMapping("ABIC") // ArticleBriefInfoC
public class ArticleBriefInfoC {

//    @Qualifier("")
    @Autowired
    private ArticleBriefInfoService articleBriefInfoService;
    @Autowired(required = false)
    private Json json;
    @RequestMapping (value = "/save", method = {RequestMethod.GET,RequestMethod.POST})
    public void saveOne(HttpServletRequest request, HttpServletResponse response){
        List<ArticleBriefInfo> list = new ArrayList<ArticleBriefInfo>();
        ArticleBriefInfo articleBriefInfo = new ArticleBriefInfo();

//        articleBriefInfo.setId();
        System.out.println(articleBriefInfo);
        articleBriefInfoService.saveOne(articleBriefInfo);
    }
    @ RequestMapping(value = "/findAll", method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject findAll(HttpServletRequest request,HttpServletResponse response){
        List<ArticleBriefInfo> list;
        list = articleBriefInfoService.findAll();
        return json.createJson(list);
    }
}