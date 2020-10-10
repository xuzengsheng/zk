package com.nccs.controller;


import com.nccs.custom.BaseCodeEnum;
import com.nccs.custom.BaseResponse;
import com.nccs.custom.BusinessException;
import com.nccs.entity.auto.UserInfo;
import com.nccs.service.auto.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @Value("${server.port}")
    private int port;


    @GetMapping("/login")
    BaseResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(password);
        UserInfo user = userInfoService.login(userInfo);
        if (user == null) {
            throw new BusinessException(BaseCodeEnum.ACCOUNT_OR_PASSOWD_WRONG);
        }
        String token = UUID.randomUUID().toString(); //生成uuid作为token
        return BaseResponse.success(token);
    }

    @GetMapping("/save")
    BaseResponse save(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setUserCode(userName);
        userInfo.setPassword(password);
        userInfo.setAge(18);
        userInfo.setSex(0);
        boolean save = userInfoService.save(userInfo);
        return BaseResponse.success(save);
    }

    @PostMapping("/getUserInfo")
    BaseResponse getUserInfo(@RequestParam("token") String token) {
        System.out.println(token);
        return BaseResponse.success(null);
    }

    @GetMapping("/port")
    BaseResponse getPort() {
        return BaseResponse.success(port);
    }
}
