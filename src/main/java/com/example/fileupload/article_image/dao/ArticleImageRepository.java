package com.example.fileupload.article_image.dao;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article_image.domain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
    List<ArticleImage> findByIdIn(List<Long> imageIdList);

    List<ArticleImage> findByArticle(Article article);
}
