package org.liubility.commons.handler;

import org.liubility.commons.exception.LBException;
import org.liubility.commons.http.response.normal.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author JDragon
 * @Date 2021.02.16 下午 5:31
 * @Email 1061917196@qq.com
 * @Des:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<String> getMessage(MethodArgumentNotValidException exception) {
        // 获取NotNull注解中的message
        String message = exception.getBindingResult().getFieldError().getDefaultMessage();
        //判断方法是否加了@AOPLogAnnotation注解
        return Result.paramsError(message);
    }

    @ExceptionHandler(value = {UnknownError.class})
    @ResponseBody
    public Result<String> getMessage(UnknownError exception) {
        return Result.unKnowError(exception.getMessage());
    }

    @ExceptionHandler(value = {LBException.class})
    @ResponseBody
    public Result<String> getMessage(LBException exception) {

        return Result.error(exception.getMessage());
    }
}