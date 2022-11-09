package com.sd.sdhr.mapper.login;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sd.sdhr.pojo.login.TDsUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TDsUserMapper extends BaseMapper<TDsUser> {

    @Select("select * from t_ds_user where USER_NO=#{UserNo}")
    TDsUser queryTDsUserByUserNo(String UserNo);
}
