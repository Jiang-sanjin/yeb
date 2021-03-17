package com.example.server.exception;


import com.example.server.pojo.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常
 *   sql的处理异常
 * @author zhanglishen
 */

@RestControllerAdvice
public class GlobalException {

    //处理sql的异常
    @ExceptionHandler(SQLException.class)
    public RespBean mySQLException(SQLException e){
        if (e instanceof SQLIntegrityConstraintViolationException){
            return RespBean.error("数据不可被修改，操作失败！");
        }
        return RespBean.error("数据库异常，操作失败！");
    }
}
