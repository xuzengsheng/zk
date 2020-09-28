package com.nccs.controller;


import com.nccs.custom.BaseResponse;
import com.nccs.entity.auto.UserInfo;
import com.nccs.service.auto.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Monroe
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;

    @PostMapping("/login")
    BaseResponse login(@RequestBody UserInfo userInfo) {
        UserInfo login = userInfoService.login(userInfo);
        login.setUserCode(UUID.randomUUID().toString()); //生成uuid作为token
        return BaseResponse.success(login);
    }


}
