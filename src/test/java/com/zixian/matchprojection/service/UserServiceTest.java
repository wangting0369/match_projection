package com.zixian.matchprojection.service;
import com.zixian.matchprojection.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;
    @Test
    public void testAddUser(){
        User user = new User();
        user.setUserName("zixian");
        user.setUserAccount("123");
        user.setAvaterUrl("");
        user.setGender(0);
        user.setUserPassword("123456");
        user.setPhone("");
        user.setEmail("");
        boolean result = userService.save(user);
        System.out.println(result);
        assertTrue(result);//断言判断
    }
}