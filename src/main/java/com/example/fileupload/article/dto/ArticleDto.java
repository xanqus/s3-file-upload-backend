package com.example.fileupload.article.dto;

import com.example.fileupload.article.domain.Article;
import com.example.fileupload.article_image.dto.ArticleImageDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ArticleDto {

    private Long id;

    private String articleUniqueId;

    private String title;

    private String body;

    private LocalDateTime createDate;

    private LocalDateTime updatedDate;

    private Boolean isTemp;

    private List<ArticleImageDto> imageList;

    public ArticleDto(Article article) {
        this.id = article.getId();

        this.articleUniqueId = article.getArticleUniqueId();

        this.title = article.getTitle();

        this.body = article.getBody();

        this.createDate = article.getCreateDate();

        this.updatedDate = article.getUpdatedDate();

        this.isTemp = article.getIsTemp();

        List<ArticleImageDto> articleImageDtoList = article.getImageList()
                .stream()
                .map(articleImage -> {
                    ArticleImageDto articleImageDto = new ArticleImageDto(articleImage);
                    return articleImageDto;
                })
                .collect(Collectors.toList());
        this.imageList = articleImageDtoList;
    }
}
