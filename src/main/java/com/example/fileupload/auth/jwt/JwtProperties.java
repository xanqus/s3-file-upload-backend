package com.example.fileupload.auth.jwt;

public interface JwtProperties {

    String SECRET = "MySecretKey";
    int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 14;
    String TOKEN_PREFIX = "Bearer ";
}
