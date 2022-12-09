package com.example.fileupload.article.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateArticleForm {


    @NotNull
    @Size(min = 1,max = 3000)
    private String body;
}
