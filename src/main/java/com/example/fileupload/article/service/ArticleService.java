package com.example.fileupload.article.service;

import com.example.fileupload.article.dao.ArticleRepository;
import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.dto.CreateArticleForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepostiory;

    public List<Article> getAllArticles() {
        return articleRepostiory.findAll();
    }

    public Article createArticle(CreateArticleForm createArticleForm) {
        Article article = Article.builder()
                .title(createArticleForm.getTitle())
                .body(createArticleForm.getBody())
                .createDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        articleRepostiory.save(article);
        return article;
    }

    public Article getArticle(Long id) {
        return articleRepostiory.findById(id).orElseThrow();
    }
}
