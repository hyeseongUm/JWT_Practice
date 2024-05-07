package com.seong.shop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

// 모든 Controller 파일 API 의 에러를 캐치
@ControllerAdvice
public class MyExceptionHandler {

    // Exception.class : 모든 에러상황
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handler(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error 발생");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> urlTypeError(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("url 타입에러");
    }
}
