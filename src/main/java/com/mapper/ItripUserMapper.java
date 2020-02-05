package com.mapper;

import com.po.ItripUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
public interface ItripUserMapper {
    //查询该用户是否存在
    public ItripUser findByUsername(String userCode);
}
