package com.example.fileupload.article.service;

import com.example.fileupload.article.dao.ArticleRepository;
import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.dto.ArticleDto;
import com.example.fileupload.article.dto.CreateArticleForm;
import com.example.fileupload.article_image.service.ArticleImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepostiory;

    private final ArticleImageService articleImageService;

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
                .title(createArticleForm.getTitle())
                .body(createArticleForm.getBody())
                .createDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        Article article1 = articleRepostiory.save(article);
        articleImageService.setArticleAtArticleImageList(article1, createArticleForm.getImageIdList());
        return article;
    }

    public ArticleDto getArticle(Long id) {
        Article article = articleRepostiory.findById(id).orElseThrow();
        ArticleDto articleDto = new ArticleDto(article);
        return articleDto;
    }

    public void deleteArticle(Long id) {
        articleRepostiory.deleteById(id);
    }
}
