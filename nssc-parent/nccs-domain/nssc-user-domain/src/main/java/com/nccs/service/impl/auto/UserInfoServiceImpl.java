package com.nccs.service.impl.auto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nccs.custom.BaseCodeEnum;
import com.nccs.entity.auto.UserInfo;
import com.nccs.custom.BusinessException;
import com.nccs.mapper.auto.UserInfoMapper;
import com.nccs.service.auto.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Monroe
 * @since 2020-09-27
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    @Override
    public UserInfo login(UserInfo userInfo) {
        if (null == userInfo) {
            throw BusinessException.fail("参数为空");
        }
        UserInfo user = userInfoMapper.selectOne(new QueryWrapper<UserInfo>()
                .lambda()
                .eq(UserInfo::getUserName, userInfo.getUserName())
                .eq(UserInfo::getPassword, userInfo.getPassword()));
        if(user == null){
            throw new BusinessException(BaseCodeEnum.ACCOUNT_OR_PASSOWD_WRONG);
        }
        user.setPassword(null);
        return user;
    }
}
