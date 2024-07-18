package com.zixian.matchprojection.service;

import com.zixian.matchprojection.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 处理业务逻辑
* @author 56308
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2024-07-09 01:59:17
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword);

    /**
     * 用户登陆
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @return 返回用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户信息脱敏
     * @param user 获取原始用户数据
     * @return 处理完敏感信息
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);



}
