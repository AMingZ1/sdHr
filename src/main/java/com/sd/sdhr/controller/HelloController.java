package com.sd.sdhr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//Spring boot核心原理：自动装配
@Controller
@RequestMapping("/hello")
public class HelloController {

    //接口：http://localhost:8080/hello
    @GetMapping("/hello")
    @ResponseBody
    public String toString() {

        return "hello world";
    }
}
