package com.sd.sdhr.service.sd.login;

import java.util.Map;

public interface LoginService {

    //用户登录
    Map<String, Object> login(String username, String password);
}
