package com.example.fileupload.article_image.dao;

import com.example.fileupload.article_image.domain.ArticleImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleImageRepository extends JpaRepository<ArticleImage, Long> {
}
