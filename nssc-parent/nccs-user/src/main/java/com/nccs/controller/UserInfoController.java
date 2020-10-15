package com.nccs.controller;


import com.nccs.custom.BaseCodeEnum;
import com.nccs.custom.BaseResponse;
import com.nccs.custom.BusinessException;
import com.nccs.service.auto.UserInfoService;
import com.nccs.user.entity.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "用户管理相关接口")  //定义该controller的描述
public class UserInfoController {
    @Autowired
    UserInfoService userInfoService;


    @Value("${server.port}")
    private int port;


    @PostMapping("/login")
    @ApiOperation("用户登录接口")
        //定义该接口的描述
//    @ApiImplicitParams({ //接口参数描述，注意：@ApiImplicitParam中的required = true与 @RequestParam 中的required = true无关联，此处打开无法正常传递参数，待解决
//            @ApiImplicitParam(name = "userName", value = "用户名", defaultValue = "admin"),
//            @ApiImplicitParam(name = "password", value = "密码", defaultValue = "123456")
//    }
//    )
    BaseResponse login(@RequestBody UserInfo userInfo) {
        UserInfo user = userInfoService.login(userInfo);
        if (user == null) {
            throw new BusinessException(BaseCodeEnum.ACCOUNT_OR_PASSOWD_WRONG);
        }
        String token = UUID.randomUUID().toString(); //生成uuid作为token
        return BaseResponse.success(token);
    }

    @PostMapping("/save")
    @ApiOperation("新增用户接口")
    BaseResponse save(@RequestBody UserInfo userInfo) {
//        UserInfo userInfo = new UserInfo();
//        userInfo.setUserName(userName);
//        userInfo.setUserCode(userName);
//        userInfo.setPassword(password);
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
