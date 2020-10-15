package com.nccs.user.feign;

import com.nccs.custom.BaseResponse;
import com.nccs.user.entity.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-15 09:48
 * @description:
 **/
@FeignClient(value = "nccs-user")
public interface UserServiceFeign {

    @PostMapping("/login")
    BaseResponse login(@RequestBody UserInfo userInfo);

    @PostMapping("/getUserInfo")
    BaseResponse getUserInfo(@RequestParam("token") String token);

}
