package com.zixian.matchprojection.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zixian.matchprojection.common.BaseResponce;
import com.zixian.matchprojection.common.ServerResponce;
import com.zixian.matchprojection.common.ResponceCode;
import com.zixian.matchprojection.contant.UserConstant;
import com.zixian.matchprojection.exception.BusinessException;
import com.zixian.matchprojection.model.User;
import com.zixian.matchprojection.model.request.UserLoginRequest;
import com.zixian.matchprojection.model.request.UserRegisterRequest;
import com.zixian.matchprojection.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册api
     * @param userRegisterRequest 注册数据
     * @return 用户id
     */
    @PostMapping("register")
    public BaseResponce<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (StringUtils.isAnyBlank(userRegisterRequest.getUserAccount(),userRegisterRequest.getUserPassword(),userRegisterRequest.getCheckPassword())){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        long data =userService.userRegister(userRegisterRequest.getUserAccount(),userRegisterRequest.getUserPassword(),userRegisterRequest.getCheckPassword());
        return ServerResponce.success(data);
    }

    /**
     * 用户登陆api
     * @param userLoginRequest
     * @return
     */
    @PostMapping("login")
    public BaseResponce<User> userRegister(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        if (StringUtils.isAnyBlank(userLoginRequest.getUserAccount(),userLoginRequest.getUserPassword())){
            throw new BusinessException(ResponceCode.PARAMS_ERROR);
        }
        User user =userService.userLogin(userLoginRequest.getUserAccount(),userLoginRequest.getUserPassword(),request);
        return ServerResponce.success(user);
    }

    /**
     * 获取当前用户信息
     * @param request 获取会话
     * @return
     */
    @GetMapping("/current")
    public BaseResponce<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        User user =userService.getSafetyUser(userService.getById(currentUser.getId()));
        return ServerResponce.success(user);
    }

    /**
     * 检验是否是管理员权限
     */
    boolean isAdmin(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null || user.getUserRole() != UserConstant.ADMIN_ROLE){
            return false;
        }
        return true;
    }

    /**
     * 管理员 根据用户名模糊查询
     */
    @GetMapping("/search")
    public BaseResponce<List<User>> searchUserList( @RequestBody String userName ,HttpServletRequest request){
        if ( !this.isAdmin(request)){
            throw new BusinessException(ResponceCode.NO_AUTH);
        }

        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)){
            queryWrapper.like("userName",userName);
        }else {
            throw new BusinessException(ResponceCode.PARAMS_ERROR,"请求参数为空");
        }

        List<User> userList = userService.list(queryWrapper);
        return ServerResponce.success(userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList()));
    }

    @PostMapping("/delete")
    public BaseResponce<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request){
        if ( !this.isAdmin(request)){
            throw new BusinessException(ResponceCode.NO_AUTH);
        }
        if (id <= 0){
            throw new BusinessException(ResponceCode.NOT_LOGIN);
        }
        return ServerResponce.success(userService.removeById(id));//自动转换为逻辑删除
    }
}
