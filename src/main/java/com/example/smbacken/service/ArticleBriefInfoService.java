package com.example.smbacken.service;
import com.example.smbacken.javabean.ArticleBriefInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleBriefInfoService{
 
 
    /**
     * 创建对象
     * @param articleBriefInfo
     */
    public void saveOne(ArticleBriefInfo articleBriefInfo);

    public List<ArticleBriefInfo> findAll();
}