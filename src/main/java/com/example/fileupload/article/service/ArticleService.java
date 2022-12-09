package com.example.fileupload.article.service;

import com.example.fileupload.article.dao.ArticleRepository;
import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.dto.ArticleDto;
import com.example.fileupload.article.dto.CreateArticleForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepostiory;

    public List<ArticleDto> getAllArticles() {
        return articleRepostiory.findAll()
                .stream()
                .map(article -> {
                    ArticleDto articleDto = new ArticleDto(article);
                    return articleDto;
                })
                .collect(Collectors.toList());
    }

    public Article createArticle(CreateArticleForm createArticleForm) {
        Article article = Article.builder()
                .body(createArticleForm.getBody())
                .createDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        articleRepostiory.save(article);
        return article;
    }

    public ArticleDto getArticle(Long id) {
        Article article = articleRepostiory.findById(id).orElseThrow();
        ArticleDto articleDto = new ArticleDto(article);
        return articleDto;
    }
}
