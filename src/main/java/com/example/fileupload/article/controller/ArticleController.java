package com.example.fileupload.article.controller;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
