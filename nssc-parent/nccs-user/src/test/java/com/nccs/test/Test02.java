package com.nccs.test;

import com.nccs.user.entity.UserInfo;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @program: nssc-parent
 * @author: xuzengsheng
 * @create: 2020-10-22 10:09
 * @description:
 **/

public class Test02 {

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
//        userInfo.setUserName("amdin");
//        Optional<UserInfo> optionalUserInfo =  Optional.of(userInfo);
        Optional<UserInfo> optionalUserInfo = Optional.ofNullable(null);
        Optional<UserInfo> optionalUserInfo1 = Optional.empty();
//        UserInfo userInfo1 = optionalUserInfo.get();

        boolean present = optionalUserInfo.isPresent();
//        System.out.println(userInfo1);
        System.out.println(present);


        Optional<Supplier<UserInfo>> sup = Optional.ofNullable(UserInfo::new);
        //调用get()方法，此时才会调用对象的构造方法，即获得到真正对象
        Optional.ofNullable(new UserInfo()).orElseGet(sup.get());
    }
}
