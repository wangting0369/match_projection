package com.zixian.matchprojection.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 相应信息
 * @param <T>
 */
@Data
public class BaseResponce<T> implements Serializable{
    private int code;

    private T data;

    private String message;

    private String description;

    public BaseResponce(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    /**
     * 主要适用于success
     * @param data
     */
    public BaseResponce(T data,ResponceCode code) {
        this.data = data;
        this.code = code.getCode();
        this.message = code.getMessage();
        this.description = code.getDescription();
    }
    /**
     * 主要适用于error
     * @param code
     */
    public BaseResponce(ResponceCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.description = code.getDescription();
    }
}
