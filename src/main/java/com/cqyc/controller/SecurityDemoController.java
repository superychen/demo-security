package com.cqyc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: cqyc
 * Description:
 * Created by cqyc on 19-10-4
 */
@RestController
@Slf4j
public class SecurityDemoController {

    @GetMapping("/")
    public String home(){
        log.info("this is demo for logback");
        return "hello cqyc home";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    /**
     * 访问此路径需要admin的权限
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/role/auth")
    public String roleAuth(){
        return "admin auth";
    }

    @PreAuthorize("#id<10 and principal.username.endsWith(#username)")
    @PostAuthorize("hasRole('ROLE_ADMIN') and returnObject%2 == 0")
    @GetMapping("/role/test/1")
    public Integer roleTest(Integer id,String username){
        return id;
    }


    @PreFilter("filterObject%2==0")
    @PostFilter("filterObject%4==0")
    @GetMapping("/role/test/2")
    public List<Integer> roleTest2(List<Integer> idList){
        return idList;
    }


}
