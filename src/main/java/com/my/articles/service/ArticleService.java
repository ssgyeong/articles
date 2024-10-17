package com.my.articles.service;

import com.my.articles.dao.ArticleDAO;
import com.my.articles.dto.ArticleDTO;
import com.my.articles.entity.Article;
import com.my.articles.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO dao;

    @Autowired
    ArticleRepository articleRepository;

    //전체보기
    public List<ArticleDTO> getAllArticle() {
        List<Article> articles = dao.getAllArticle();
        if(ObjectUtils.isEmpty(articles)) {
            return Collections.emptyList();
        }
        List<ArticleDTO> dtoList = articles
                .stream().map(x -> ArticleDTO.fromArticle(x)).toList();
        return  dtoList;
    }

    //상세보기
    public ArticleDTO getOneArticle(Long id) {
        Article article = dao.getOneArticle(id);
        if(ObjectUtils.isEmpty(article)) return null;
        return ArticleDTO.fromArticle(article);
    }

    //삭제
    public void deleteArticle(Long id) {
        dao.deleteArticle(id);
    }

    //수정
    public void updateArticle(ArticleDTO dto) {
        dao.updateArticle(dto);
    }

    //새 게시글
    public void insertArticle(ArticleDTO dto) {
        dao.insertArticle(ArticleDTO.fromDto(dto));
    }

    public Page<ArticleDTO> getArticlePage(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        return articles.map(x->ArticleDTO.fromArticle(x));
    }
}
