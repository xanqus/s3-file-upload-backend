package com.example.fileupload.article_image.service;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article_image.dao.ArticleImageRepository;
import com.example.fileupload.article_image.domain.ArticleImage;
import com.example.fileupload.article_image.dto.ArticleImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleImageService {

    private final ArticleImageRepository articleImageRepository;

    public ArticleImageDto addArticleImage(String imgUrl, Article article) {
        ArticleImage articleImage = ArticleImage.builder()
                .imgUrl(imgUrl)
                .article(article)
                .build();
        ArticleImage articleImage1 = articleImageRepository.save(articleImage);
        ArticleImageDto dto = new ArticleImageDto(articleImage1);
        return dto;
    }


    public void setArticleAtArticleImageList(Article article1, List<Long> imageIdList) {
        List<ArticleImage> articleImageList = articleImageRepository.findByIdIn(imageIdList);
        System.out.println("게시물에 등록된 사진 개수: " + articleImageList.size());
        articleImageList
                .stream()
                .forEach(
                        articleImage -> {
                            articleImage.setArticle(article1);
                            articleImageRepository.save(articleImage);
                        }
                );
    }
}
