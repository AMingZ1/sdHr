package com.sd.sdhr.service.sd.login.impl;

import com.sd.sdhr.mapper.login.TDsUserMapper;
import com.sd.sdhr.pojo.login.TDsUser;
import com.sd.sdhr.service.sd.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TDsUserMapper tDsUserMapper;

    @Override
    public Map<String, Object> login(String userId,String passWord) {
        Map<String,Object> map = new HashMap<>();
        TDsUser tDsUser=tDsUserMapper.queryTDsUserByUserNo(userId);
        if (tDsUser!=null && passWord.equals(tDsUser.getUserPassword())){
            map.put("success","1");
            map.put("message","登录成功");
            map.put("token",tDsUser.getUserPassword());
            map.put("userId",tDsUser.getUserNo());
            map.put("userName",tDsUser.getUserName());
        }else{
            map.put("success","-1");
            map.put("message","登录失败");
            map.put("token"," ");
        }
        return map;
    }
}
