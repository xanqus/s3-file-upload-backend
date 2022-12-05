package com.example.fileupload.article.controller;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.dto.CreateArticleForm;
import com.example.fileupload.article.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("")
    public List<Article> getArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping("")
    public void createArticle(@Valid @RequestBody CreateArticleForm createArticleForm) {

        articleService.createArticle(createArticleForm);
    }
}
