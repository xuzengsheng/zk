package com.nccs.user.controller;

import com.nccs.custom.BaseResponse;
import com.nccs.user.entity.UserInfo;
import com.nccs.user.feign.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-15 09:58
 * @description:
 **/
@RestController
@RequestMapping("/userInfo")
public class UserServerController {

    @Autowired
    UserServiceFeign userServiceFeign;

    @PostMapping("/login")
    BaseResponse login(@RequestBody UserInfo userInfo) {
        System.out.println("正在使用feign调用nccs-user的login方法");
        return userServiceFeign.login(userInfo);
    }

}
