package com.cqyc.controller;

import com.cqyc.domain.test.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-5
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage(){
        System.out.println("---------------");
        return "login";
    }

    @PostMapping("/login/user")
    @ResponseBody
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user",user);
            return "登录成功";
        }catch (Exception e){
            throw new RuntimeException("登录出现异常,请检查账号密码");
        }
    }


    @GetMapping("/admin")
    @ResponseBody
    public String admin(){
        return "当前登录的账户是管理员才能看见的东西";
    }

    @GetMapping("/login/out")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            subject.logout();
        }
        return "login";
    }

    /**
     * 未授权走的路径
     */
    @GetMapping("unauthorized")
    @ResponseBody
    public String unauthorized(){
        return "未认证用户,请先登录";
    }

    @GetMapping("edit")
    @ResponseBody
    public String editTest(){
        return "edit successl";
    }
}
