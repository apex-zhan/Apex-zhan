package com.itheima.exception;


import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理类
@RestControllerAdvice
public class GobaiExceptionHandler {
    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("执行了全局异常处理,请联系管理员");
    }


}


