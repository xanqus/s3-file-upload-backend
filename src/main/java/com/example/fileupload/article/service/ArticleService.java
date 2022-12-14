package com.example.fileupload.article.service;

import com.amazonaws.services.s3.AmazonS3;
import com.example.fileupload.article.dao.ArticleRepository;
import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article.dto.ArticleDto;
import com.example.fileupload.article.dto.CreateArticleForm;
import com.example.fileupload.article_image.domain.ArticleImage;
import com.example.fileupload.article_image.service.ArticleImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepostiory;

    private final ArticleImageService articleImageService;

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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
        Article article = articleRepostiory.findById(id).orElseThrow();

        List<ArticleImage> articleImageList = articleImageService.getArticleImageByArticle(article);

        articleImageList
                .stream()
                        .forEach(articleImage -> {
                            String imgUrl = articleImage.getImgUrl();
                            String filename = imgUrl.substring(imgUrl.lastIndexOf("/") + 1, imgUrl.length());
                            amazonS3.deleteObject(bucket, filename);

                        });

        articleRepostiory.deleteById(id);

    }

    public void createTmpArticle(String articleUniqueId, CreateArticleForm createArticleForm) {
        Article article = Article.builder()
                .articleUniqueId(articleUniqueId)
                .title(createArticleForm.getTitle())
                .body(createArticleForm.getBody())
                .createDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .isTemp(true)
                .build();
        articleRepostiory.save(article);
    }

    public ArticleDto getTmpArticle(String articleUniqueId) {
        Article article = articleRepostiory.findByArticleUniqueId(articleUniqueId);
        ArticleDto articleDto = new ArticleDto(article);
        return articleDto;
    }

    public void patchTmpArticle(String articleUniqueId, CreateArticleForm createArticleForm) {
        Article article = articleRepostiory.findByArticleUniqueId(articleUniqueId);

        article.setTitle(createArticleForm.getTitle());
        article.setBody(createArticleForm.getBody());
        articleRepostiory.save(article);
    }

    public void patchCompleteSaveArticle(String articleUniqueId, CreateArticleForm createArticleForm) {
        Article article = articleRepostiory.findByArticleUniqueId(articleUniqueId);
        article.setTitle(createArticleForm.getTitle());
        article.setBody(createArticleForm.getBody());
        article.setIsTemp(false);
        articleRepostiory.save(article);
    }
}
