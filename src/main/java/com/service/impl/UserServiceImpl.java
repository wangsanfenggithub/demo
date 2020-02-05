package com.service.impl;

import com.mapper.ItripUserMapper;
import com.po.ItripUser;
import com.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

/**
 * 用户管理接口的实现
 *
 * @author hduser
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private ItripUserMapper itripUserMapper;

    public ItripUserMapper getItripUserMapper() {
        return itripUserMapper;
    }

    public void setItripUserMapper(ItripUserMapper itripUserMapper) {
        this.itripUserMapper = itripUserMapper;
    }

    /**
     * 创建用户
     */
    public void itriptxCreateUserByPhone(ItripUser user) {

    }

    /*
     * 查询该用户是否存在
     * */
    public ItripUser findByUsername(String userCode) {
        System.out.println("444444444444");
        if (itripUserMapper != null) {
            System.out.println("555555555555555");
            return itripUserMapper.findByUsername(userCode);
        }
        return null;
    }


}
