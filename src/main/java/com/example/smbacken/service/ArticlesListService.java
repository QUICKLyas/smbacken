package com.example.smbacken.service;
import com.example.smbacken.javabean.ArticlesList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticlesListService {
    public List<ArticlesList> findArticlesList();

    /**
     *
     * @param condition
     * @param pagination
     * @return List<ArticlesList>
     */
    public List<ArticlesList> findArticlesListSort(String condition,int...pagination);
    public List<ArticlesList> findArticlesListSort(String condition);

    /**
     *
     * @param string
     * @return List<String>
     */
    List<Object> searchElement(String condition);
}
