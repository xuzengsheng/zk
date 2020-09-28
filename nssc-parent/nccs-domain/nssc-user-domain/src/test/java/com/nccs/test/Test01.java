package com.nccs.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nccs.UserServiceApp;
import com.nccs.custom.BaseResponse;
import com.nccs.entity.auto.UserInfo;
import com.nccs.mapper.auto.UserInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: nccs-parent
 * @author: xuzengsheng
 * @create: 2020-09-27 13:55
 * @description:
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApp.class)
@Slf4j
public class Test01 {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void test1() {
        UserInfo userInfo = userInfoMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getId, 1));
        System.out.println(userInfo);
    }

}