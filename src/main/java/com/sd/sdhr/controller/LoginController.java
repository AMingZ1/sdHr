package com.sd.sdhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

   /* @RequestMapping("/index")
    public String login(@RequestParam("userId") String userId,
                        @RequestParam("passworld")String passWorld,
                        Model model,
                        HttpSession session) {
        if("admin".equals(userId) && "123".equals(passWorld)){
            session.setAttribute("userId",userId);
            return  "redirect:/admin-dashboard";
        }else {
            model.addAttribute("msg","用户名或密码错误！");
            return  "index";
        }
    }*/
}
