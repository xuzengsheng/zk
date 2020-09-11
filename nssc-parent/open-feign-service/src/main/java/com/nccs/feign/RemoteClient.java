package com.nccs.feign;

import com.nccs.feign.fallback.RemoteClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-11 09:00
 * @description:
 **/
@FeignClient(name = "nacos-provide", fallback = RemoteClientFallback.class)
public interface RemoteClient {

    @GetMapping("/provide/helloNacos")
    String helloNacos();

}
