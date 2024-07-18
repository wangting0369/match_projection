package com.zixian.matchprojection.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * implements Serializable进行序列化
 * serialVersionId防止序列化过程中产生冲突，安装插件
 */
@Data
public class UserRegisterRequest  {

    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
