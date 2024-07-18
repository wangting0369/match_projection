package com.zixian.matchprojection.exception;

import com.zixian.matchprojection.common.ResponceCode;

/**
 * 自定义异常类
 * 和BaseResponce同等及以上的地位
 */
public class BusinessException extends RuntimeException{
    private int code;
    private String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }
    public BusinessException(ResponceCode code) {
        super(code.getMessage());
        this.code = code.getCode();
        this.description = code.getDescription();
    }
    public BusinessException(ResponceCode code,String description) {
        super(code.getMessage());
        this.code = code.getCode();
        this.description = description;
    }
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
