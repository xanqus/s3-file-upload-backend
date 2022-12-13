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
@RequestMapping("/article")
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
        createArticleForm.getImageIdList()
                .stream()
                        .forEach(id->{
                            System.out.println("id: " + id);
                        });

        // 게시물 작성 후 db 저장 로직
        articleService.createArticle(createArticleForm);

    }
}
