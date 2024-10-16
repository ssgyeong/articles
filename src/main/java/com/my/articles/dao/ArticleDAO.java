package com.my.articles.dao;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
@Transactional
@Slf4j
public class ArticleDAO {
    @Autowired
    EntityManager em;

    public List<Article> getAllArticle() {
        String sql = "SELECT a FROM Article a " +
                "ORDER BY a.id DESC";
        List<Article> articles = em.createQuery(sql).getResultList();
        return articles;
    }

    public Article getOneArticle(Long id) {
        Article article = em.find(Article.class, id);
        log.info(article.toString());
        return article;
    }

    public void deleteArticle(Long id) {
        Article article = em.find(Article.class, id);
        em.remove(article);
    }

    public void updateArticle(ArticleDTO dto) {
        Article article = em.find(Article.class, dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
    }

    public void insertArticle(Article article) {
        em.persist(article);
    }
}