package com.zixian.matchprojection.common;

import com.zixian.matchprojection.exception.BusinessException;

/**
 * BaseResponce封装的语法糖
 * 方法类
 */
public class ServerResponce {
    /**
     * success
     */
    static public <T> BaseResponce<T> success(){
        return new BaseResponce<>(ResponceCode.SUCCESS);
    }
    static public <T> BaseResponce<T> success(T data ){
        return new BaseResponce<>(data,ResponceCode.SUCCESS);
    }

    /**
     * error
     */
    static public <T> BaseResponce<T> error(ResponceCode code){
        return new BaseResponce<>(code);
    }

    static public <T> BaseResponce<T> error(BusinessException e){
        return new BaseResponce<>(e.getCode(),null,e.getMessage(),e.getDescription());
    }


}
