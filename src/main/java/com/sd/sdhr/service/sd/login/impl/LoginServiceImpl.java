package com.sd.sdhr.service.sd.login.impl;

import com.sd.sdhr.mapper.login.TDsUserMapper;
import com.sd.sdhr.pojo.login.TDsUser;
import com.sd.sdhr.service.common.JwtUtil;
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
            map.put("userId",tDsUser.getUserNo());
            map.put("userName",tDsUser.getUserName());
            String token=JwtUtil.generateToken(tDsUser.getUserName(),tDsUser.getUserNo());
            map.put("token",token);
        }else{
            map.put("success","-1");
            map.put("message","登录失败");
            map.put("token"," ");
        }
        return map;
    }

    @Override
    public Map<String, Object> getUserMsg(String userId) {
        Map<String,Object> map = new HashMap<>();
        TDsUser tDsUser=tDsUserMapper.queryTDsUserByUserNo(userId);
        if (tDsUser !=null ){
            map.put("success","1");
            map.put("message","查询成功");
            map.put("userId",tDsUser.getUserNo());
            map.put("userName",tDsUser.getUserName());
            map.put("jobs",tDsUser.getJobs());
        }else{
            map.put("success","-1");
            map.put("message","查询失败");
            map.put("token"," ");
        }
        return map;
    }
}
