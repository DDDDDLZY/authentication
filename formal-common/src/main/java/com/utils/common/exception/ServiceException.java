package com.utils.common.exception;
/*
* 自定义异常
* */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -7594038810680378032L;
    public ServiceException(){}
    public ServiceException(String message){
        super(message);
    }
    public ServiceException(Throwable cause){
        super(cause);
    }
}
