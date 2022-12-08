package com.example.fileupload.article_image.dto;

import com.example.fileupload.article_image.domain.ArticleImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleImageDto {

    private Long id;

    private String imgUrl;

    public ArticleImageDto(ArticleImage articleImage) {
        this.id = articleImage.getId();
        this.imgUrl = articleImage.getImgUrl();
    }
}
