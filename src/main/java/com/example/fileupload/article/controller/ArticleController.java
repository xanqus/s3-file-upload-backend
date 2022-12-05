package com.example.fileupload.article.controller;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.dto.CreateArticleForm;
import com.example.fileupload.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public List<Article> getArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping("")
    public void createArticle(@RequestBody CreateArticleForm createArticleForm) {

        articleService.createArticle(createArticleForm);
    }
}
