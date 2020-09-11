package com.nccs.feign.fallback;

import com.nccs.feign.RemoteClient;
import org.springframework.stereotype.Component;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-09-11 09:02
 * @description:
 **/
@Component
public class RemoteClientFallback implements RemoteClient {
    @Override
    public String helloNacos() {
        return "调用nacos-provide服务超时了";
    }
}
