//package com.cqyc.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
///**
// * @author: cqyc
// * Description:
// * Created by cqyc on 19-10-4
// */
////todo 暂时关闭security启动类
/////@Configuration
////@EnableWebSecurity
//public class WebSecurityImpl extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private MyUserDetail myUserDetail;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("cqyc").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
////        auth.userDetailsService(myUserDetail).passwordEncoder(new MyPasswordEncoder());
//
//        //这里必须需要spring security自定义的表结构
////        auth.jdbcAuthentication().usersByUsernameQuery("").authoritiesByUsernameQuery("").passwordEncoder(new MyPasswordEncoder());
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .logout().permitAll()
//                .and()
//                .formLogin();
//        http.csrf().disable();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/js/**","/css/**","image/**");
//    }
//
//}
