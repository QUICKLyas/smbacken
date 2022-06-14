package com.example.smbacken.service.Impl;

import com.example.smbacken.javabean.ArticleBriefInfo;
import com.example.smbacken.service.ArticleBriefInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("ArticlesBriefInfoService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ArticleBriefInfoServiceImpl implements ArticleBriefInfoService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @param articleBriefInfo
     */
    @Override
    public void saveOne(ArticleBriefInfo articleBriefInfo) {
        mongoTemplate.save(articleBriefInfo);
    }

    @Override
    public List<ArticleBriefInfo> findAll() {
        return mongoTemplate.findAll(ArticleBriefInfo.class);
    }
}