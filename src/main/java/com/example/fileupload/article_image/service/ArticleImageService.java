package com.example.fileupload.article_image.service;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article_image.dao.ArticleImageRepository;
import com.example.fileupload.article_image.domain.ArticleImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleImageService {

    private final ArticleImageRepository articleImageRepository;

    public void addArticleImage(String imgUrl, Article article) {
        ArticleImage articleImage = ArticleImage.builder()
                .imgUrl(imgUrl)
                .article(article)
                .build();
        articleImageRepository.save(articleImage);
    }
}
