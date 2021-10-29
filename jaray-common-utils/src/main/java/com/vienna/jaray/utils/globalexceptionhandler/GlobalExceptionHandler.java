package com.vienna.jaray.utils.globalexceptionhandler;

import com.vienna.jaray.utils.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Jaray
 * @date 2020年09月10日 22:29
 * @description: 全局异常处理类
 * 控制器增强类，当控制器发生异常且符合类中定义的拦截异常类，将会被拦截。可以定义拦截的控制器所在的包路径
 *
 * 异常处理会导致事务不回滚，调用方需要根据返回值throw new RuntimeException(msg)使事务回滚
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResponseResult globalException(HttpServletResponse response, Exception e) throws Exception {
        log.info("GlobalExceptionHandled...{}", e);
        String msg = "GlobalExceptionHandler:"+e.getMessage();

        return ResponseResult.error().setMsg(msg);
    }
}
