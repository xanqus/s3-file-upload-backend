package com.example.fileupload.article.domain;

import com.example.fileupload.article_image.domain.ArticleImage;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    private LocalDateTime createDate;

    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "article")
    private List<ArticleImage> imageList;
}
