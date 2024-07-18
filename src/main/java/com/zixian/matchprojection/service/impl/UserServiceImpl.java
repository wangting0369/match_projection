package com.zixian.matchprojection.service.impl;
import java.util.Date;

import ch.qos.logback.core.util.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zixian.matchprojection.common.ResponceCode;
import com.zixian.matchprojection.contant.UserConstant;
import com.zixian.matchprojection.exception.BusinessException;
import com.zixian.matchprojection.model.User;
import com.zixian.matchprojection.service.UserService;
import com.zixian.matchprojection.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户服务实现类
* @author 56308
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-07-09 01:59:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        //1 校验
        if (StringUtils.isAnyBlank(userAccount,userPassword,checkPassword)){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        String valinPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(valinPattern).matcher(userAccount);
        if (!matcher.find()){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (!userPassword.equals(checkPassword)){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("userAccount",userAccount);
        long count = this.count(queryWrapper);
        if (count > 0 ){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }

        //加密 加密算法就用现成的
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        if (!this.save(user)){
            throw new BusinessException(ResponceCode.DEAL_ERROR);
        }
        return user.getId();

    }


    @Override
    public User userLogin(String userAccount, String userPassword,HttpServletRequest request) {
        //1 校验
        if (StringUtils.isAnyBlank(userAccount,userPassword)){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (userAccount.length() < 4){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (userPassword.length() < 8 ){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        String valinPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(valinPattern).matcher(userAccount);
        if (!matcher.find()){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }

        //查询账户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("userAccount",userAccount);
        long count = this.count(queryWrapper);
        if (count == 0 ){
            throw new BusinessException(ResponceCode.NULL_ERROR,"查询账户不存在");
        }

        //查询密码是否正确  加密 加密算法就用现成的
        String encryptPassword = DigestUtils.md5DigestAsHex(userPassword.getBytes(StandardCharsets.UTF_8));
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<User>().eq("userAccount",userAccount).eq("userPassword",encryptPassword);
        User user = this.getOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ResponceCode.NULL_ERROR,"密码输入错误");
        }
        User safetyUser = getSafetyUser(user);
        //数据脱敏
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE,safetyUser);
        return safetyUser;
    }

    @Override
    public User getSafetyUser(User user) {
        if(user == null){
            throw new BusinessException(ResponceCode.DEAL_ERROR);
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUserName(user.getUserName());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvaterUrl(user.getAvaterUrl());
        safetyUser.setGender(user.getGender());
        safetyUser.setUserPassword(null);
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getEmail());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUpdateTime(user.getUpdateTime());
        safetyUser.setIsDelete(user.getIsDelete());
        safetyUser.setUserRole(user.getUserRole());
        return safetyUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return 1;
    }

}




