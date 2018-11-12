package com.ssm.promotion.core.exception;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ssm.promotion.core.common.Result;

public class BusinessExceptionController {

	protected static Logger logger = LoggerFactory.getLogger(BusinessExceptionController.class);

    @ExceptionHandler(SQLException.class)  
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
    public @ResponseBody Result<String> handleSQLException(HttpServletRequest request,  
            SQLException ex) {
    	Result<String> result = new Result<String>();
    	result.setResultCode(-1);
    	result.setMessage("handleSQLException 数据库操作异常");
    	logger.info("handleSQLException - 异常" + ex.getMessage());
    	return result;
    }

    @ExceptionHandler(BusinessException.class)  
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody Result<String> handleBadRequestException(
            HttpServletRequest request, BusinessException ex) {
    	logger.info("handleBadRequestException - BusinessException");
    	Result<String> result = new Result<String>();
    	result.setResultCode(-1);
    	result.setMessage("handleBadRequestException 请求错误");
    	logger.info("handleBadRequestException - 异常" + ex.getMessage());
    	return result;
    }  
    
    @ExceptionHandler(SystemException.class)  
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)  
    public @ResponseBody Result<String> handleSystemException(HttpServletRequest request,  
            SystemException ex) {
    	Result<String> result = new Result<String>();
    	result.setResultCode(-1);
    	result.setMessage("handleSystemException - 系统错误");
    	logger.info("handleSystemException - 异常" + ex.getMessage());
    	return result;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Result<String> handleAllException(HttpServletRequest request,
            Exception ex) {
    	Result<String> result = new Result<String>();
    	result.setResultCode(-1);
    	result.setMessage("handleAllException - 异常");
    	logger.info("handleAllException - 异常" + ex.getMessage());
    	return result;
    }
} 
