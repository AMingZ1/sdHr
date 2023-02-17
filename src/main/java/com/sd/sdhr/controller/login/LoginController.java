package com.sd.sdhr.controller.login;

import com.sd.sdhr.service.common.JwtUtil;
import com.sd.sdhr.service.sd.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/Login")
    public Map<String, Object> login(String userId, String passWord, HttpServletRequest request) {
        //@RequestParam("userId")
        Map<String,Object> maps = new HashMap<>();
        if (userId==null || passWord==null){
            maps.put("success","-1");
            maps.put("message","登录失败");
            maps.put("token"," ");
        }else {
            maps=loginService.login(userId,passWord);
            if ("1".equals(maps.get("success").toString())){
                //将账号登录信息存放到session

                HttpSession session = request.getSession();
                session.setAttribute("userId",maps.get("userId").toString());
                session.setAttribute("userName",maps.get("userName").toString());
            }

        }
        return maps;
    }

    @RequestMapping("/Login/getUser")
    public Map<String, Object> login(String userId) {
        //@RequestParam("userId")
        Map<String,Object> maps = new HashMap<>();
        if (userId==null){
            maps.put("success","-1");
            maps.put("message","查询信息失败!");
            maps.put("token"," ");
        }else {
            maps=loginService.getUserMsg(userId);
        }
        return maps;
    }

    @RequestMapping("/Login/getUsers")
    public Map<String, Object> login(HttpServletRequest request) {
        //@RequestParam("userId")
        Map<String,Object> maps = new HashMap<>();
        Enumeration<String> ss= request.getHeaderNames();
        return maps;
    }
}
