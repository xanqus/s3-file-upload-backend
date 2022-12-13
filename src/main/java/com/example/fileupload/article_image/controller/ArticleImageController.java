package com.example.fileupload.article_image.controller;

import com.example.fileupload.article_image.service.ArticleImageService;
import com.example.fileupload.aws.service.AwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/article/image")
@RequiredArgsConstructor
public class ArticleImageController {

    private final AwsService awsService;

    private final ArticleImageService articleImageService;



    @PostMapping("")
    public String createImageUrl(@RequestParam("file") MultipartFile file) throws IOException {

        String imgUrl = awsService.sendFileToS3Bucket(file);
        articleImageService.addArticleImage(imgUrl, null);
        return imgUrl;

    }
}
