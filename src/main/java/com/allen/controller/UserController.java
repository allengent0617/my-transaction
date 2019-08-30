package com.allen.controller;

import com.allen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : allengent@163.com
 * @date : 2019/8/29 23:01
 * description :
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/test1")
    public void  test1()
    {
        userService.test1();
    }

    @RequestMapping(value = "/test2")
    public void  test2()
    {
        userService.test2();
    }

}
