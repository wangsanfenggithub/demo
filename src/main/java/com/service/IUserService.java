package com.service;

import com.po.ItripUser;

public interface IUserService {
    /**
     * 使用手机号创建用户账号
     *
     * @param user
     * @throws Exception
     */
    public void itriptxCreateUserByPhone(ItripUser user);

    //查询该用户是否存在
    public ItripUser findByUsername(String userCode);

}

