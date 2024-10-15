package com.my.articles.dao;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
@Transactional
public class ArticleDAO {
    @Autowired
    EntityManager em;

    //전체보기
    public List<Article> getAllArticle() {
        String sql = "SELECT a FROM Article a ORDER BY a.id DESC";
        return em.createQuery(sql).getResultList();
    }

    //상세보기
    public Article getOneArticle(Long id) {
        return em.find(Article.class, id);
    }

    //삭제
    public void deleteArticle(Long id) {
        Article article = em.find(Article.class, id);
        em.remove(article);
    }

    //수정
    public void updateArticle(ArticleDTO dto) {
        Article article = em.find(Article.class, dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
    }

    //새 게시글
    public void insertArticle(Article article) {
        em.persist(article);
    }
}