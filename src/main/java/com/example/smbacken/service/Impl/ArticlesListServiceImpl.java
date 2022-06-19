package com.example.smbacken.service.Impl;

import com.example.smbacken.javabean.ArticlesList;
import com.example.smbacken.service.ArticlesListService;
import com.example.smbacken.util.trie.TrieTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
@Service("ArticlesListService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ArticlesListServiceImpl implements ArticlesListService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @return List<ArticlesList>
     */
    @Override
    public List<ArticlesList> findArticlesList() {
        return mongoTemplate.findAll(ArticlesList.class);
    }

    /**
     * pagination传入数据必需为两个或者没有，且输入两个的时候pageNow必需在前，pageSize在后
     * @param condition
     * @param pagination
     * @return List<ArticlesList>
     */
    @Override
    public List<ArticlesList> findArticlesListSort(String condition, int... pagination) {
        // 条件组
        Query query = new Query();
        // 排序限制
        Sort sort = Sort.by(Sort.Direction.DESC,condition);
        query.with(sort);
        // 分页限制
        PageRequest pageRequest = PageRequest.of(pagination[0],pagination[1]);
        query.with(pageRequest);
        return mongoTemplate.find(query,ArticlesList.class);
    }

    @Override
    public List<ArticlesList> findArticlesListSort(String condition) {
        // 条件组
        Query query = new Query();
        // 排序限制
        Sort sort = Sort.by(Sort.Direction.DESC,condition);
        return mongoTemplate.find(query,ArticlesList.class);
    }


    @Override
    public List<Object> searchElement(String condition) {
        System.out.println(condition);
        Pattern pattern = Pattern.compile("^.*"+condition+".*$", Pattern.CASE_INSENSITIVE);
        // 设置查询条件
        Query findQuery = new Query();
        findQuery.addCriteria(Criteria.where("title").regex(pattern));
        // 排序限制
        Sort sort = Sort.by(Sort.Direction.DESC,"views");
        findQuery.with(sort);
        // 查询结果
        List<ArticlesList> rList = mongoTemplate.find(findQuery,ArticlesList.class);
        // 截取结果中特定的字段
        List<String> mapList =rList.stream().map(ArticlesList::getTitle).collect(Collectors.toList());
        // 匹配字段
        TrieTree trieTree = new TrieTree();
        for (String word : mapList){
            trieTree.insert(word);
        }
        List list = new ArrayList(trieTree.getWordsForPrefix(condition,""));
        if (list.size() > 10){
            list = list.subList(0,9);
        }
        return Collections.singletonList(list);
    }
}

