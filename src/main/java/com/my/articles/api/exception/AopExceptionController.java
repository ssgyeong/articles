package com.my.articles.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // RestController에서 잘못된 요청 들어오면 자동으로 감지 + 처리
public class AopExceptionController {
    public ResponseEntity<ApiResponse> badRequestError(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder().message(e.getMessage()).build());
    }
}
