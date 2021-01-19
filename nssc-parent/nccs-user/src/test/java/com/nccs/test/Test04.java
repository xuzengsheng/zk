package com.nccs.test;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.nccs.UserServiceApp;
import com.nccs.mapper.auto.UserInfoMapper;
import com.nccs.user.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-23 08:58
 * @description:
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApp.class)
@Slf4j
public class Test04 {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    public void test1() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("aaaaaaaaaaaaaaaaaa");
        userInfo.setUserCode("22222");
        userInfo.setPassword("123456");
        userInfo.setAge(20);
        userInfo.setSex(1);
        try {
            userInfoMapper.insert(userInfo);
        } catch (Exception e) {
            if(e instanceof DataIntegrityViolationException)
            System.out.println("===="+e.getMessage());
        }
    }
}
