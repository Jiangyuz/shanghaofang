package com.example.service;

import com.example.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo> {
    //根据手机号查询用户
    UserInfo getByPhone(String phone);
}
