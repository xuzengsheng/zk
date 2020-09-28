package com.nccs.service.auto;

import com.nccs.entity.auto.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Monroe
 * @since 2020-09-27
 */
public interface UserInfoService extends IService<UserInfo> {

    UserInfo login(UserInfo userInfo);
}
