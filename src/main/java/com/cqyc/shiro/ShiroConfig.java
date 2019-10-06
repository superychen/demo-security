package com.cqyc.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @author: cqyc
 * Description: shiro核心配置类
 * Created by cqyc on 19-10-5
 */
@Configuration
@Slf4j
public class ShiroConfig {

    /**
     * 这里配置CredentialsMatcher
     */
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //设置MD5算法加密
        matcher.setHashAlgorithmName("MD5");
        //进行加密的算法次数
        matcher.setHashIterations(10);
        return matcher;
    }

    /**
     * 配置自定义的权限登录器
     */
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        AuthRealm authRealm = new AuthRealm();
        //相关的认证东西可以缓存到内存中
        authRealm.setCacheManager(new MemoryConstrainedCacheManager());
        authRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return authRealm;
    }

    /**
     *权限管理，配置主要是Realm的管理认证,核心安全事务管理器
     */
    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm){
        log.debug("--------加载shiroSecurityManager----------");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        return  securityManager;
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        //未授权的页面
        bean.setUnauthorizedUrl("/unauthorized");
        //配置访问权限，相当于xml中的filter
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/druid/**","anon");
        map.put("/login/**","anon");
        //表示admin访问必须由admin这个角色来访问
        map.put("/admin","roles[admin]");
        //权限控制,只有拥有edit这个权限的人才能访问
        map.put("/edit","perms[edit]");
        map.put("/**","authc");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }


    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            @Qualifier("securityManager") SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

}
