package com.zixian.matchprojection.common;

import lombok.AllArgsConstructor;

/**
 * 错误码
 * 属性默认是 public static
 * name new 实例
 */
@AllArgsConstructor
public enum ResponceCode {
    SUCCESS(0,"OK",""),
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据为空",""),
    DEAL_ERROR(40002,"数据处理错误",""),
    NOT_LOGIN(40100,"未登录",""),
    NO_AUTH(40101,"无权限",""),
    Runtime_Exception(50000,"运行错误","");



    private final int code;

    private final String message;
    private final String description;
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
