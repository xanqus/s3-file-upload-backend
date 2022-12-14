package com.example.fileupload.article.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fileupload.article.dto.ArticleDto;
import com.example.fileupload.article.dto.CreateArticleForm;
import com.example.fileupload.article.service.ArticleService;
import com.example.fileupload.article_image.service.ArticleImageService;
import com.example.fileupload.aws.service.AwsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final ArticleService articleService;

    private final ArticleImageService articleImageService;

    private final AwsService awsService;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @GetMapping("")
    public List<ArticleDto> getArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public ArticleDto getArticles(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @PostMapping("")
    public void createArticle(@Valid CreateArticleForm createArticleForm) throws IOException {

        // 게시물 작성 후 db 저장 로직
        articleService.createArticle(createArticleForm);

    }

    @DeleteMapping("")
    public void deleteArticle(@RequestParam("id") Long id) {

        articleService.deleteArticle(id);
    }

    // 임시 저장된 article 가져오기
    @GetMapping("/tmp")
    public ArticleDto getTmpArticle(@RequestParam("articleUniqueId") String articleUniqueId) {
        return articleService.getTmpArticle(articleUniqueId);
    }

    // 게시물 임시저장 로직
    @PostMapping("/tmpSave")
    public void tmpSaveArticle(@RequestParam("articleUniqueId") String articleUniqueId, @Valid CreateArticleForm createArticleForm) {

        articleService.createTmpArticle(articleUniqueId, createArticleForm);
    }

    @PatchMapping("/tmpSave")
    public void tmpSaveArticlePatch(@RequestParam("articleUniqueId") String articleUniqueId, @Valid CreateArticleForm createArticleForm) {
        articleService.patchTmpArticle(articleUniqueId, createArticleForm);

    }

    @PatchMapping("/completeSave")
    public void completeSaveArticlePatch(@RequestParam("articleUniqueId") String articleUniqueId, @Valid CreateArticleForm createArticleForm) {
        articleService.patchCompleteSaveArticle(articleUniqueId, createArticleForm);

    }
}
