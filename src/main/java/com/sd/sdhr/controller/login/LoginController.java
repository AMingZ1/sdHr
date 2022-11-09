package com.sd.sdhr.controller.login;

import com.sd.sdhr.service.sd.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/Login")
    public Map<String, Object> login(String userId, String passWord, HttpSession session) {
        //@RequestParam("userId")
        Map<String,Object> maps = new HashMap<>();
        if (userId==null || passWord==null){
            maps.put("success","-1");
            maps.put("message","登录失败");
            maps.put("token"," ");
        }else {
            maps=loginService.login(userId,passWord);
            if ("1".equals(maps.get("success").toString())){
                session.setAttribute("userId",maps.get("userId").toString());
                session.setAttribute("userName",maps.get("userName").toString());
            }

        }
        return maps;
    }
}
