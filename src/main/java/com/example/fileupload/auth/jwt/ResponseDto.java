package com.example.fileupload.auth.jwt;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {

    private String code;

    private String message;
}
