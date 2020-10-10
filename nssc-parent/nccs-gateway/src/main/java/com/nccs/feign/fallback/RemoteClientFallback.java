package com.nccs.feign.fallback;

import com.nccs.custom.BaseResponse;
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
    public BaseResponse getUserInfo(String token) {
        return BaseResponse.fail("调用nccs-user服务超时");
    }
}
