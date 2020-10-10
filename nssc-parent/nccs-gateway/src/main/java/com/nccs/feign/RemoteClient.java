package com.nccs.feign;

import com.nccs.custom.BaseResponse;
import com.nccs.feign.fallback.RemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-11 09:00
 * @description:
 **/
@FeignClient(name = "nccs-user", fallback = RemoteClientFallback.class)
public interface RemoteClient {

    @PostMapping("/userInfo/getUserInfo")
    BaseResponse getUserInfo(@RequestParam("token") String token);

}
