package com.example.admin.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.admin.service.PermissionService;

import com.example.admin.shiro.MyshiroRealm;
import com.example.common.entity.Permission;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Autowired
    private PermissionService permissionService;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.password}")
    private String password;
    @Bean
    public MyshiroRealm getMyShiroRealm(){
        MyshiroRealm myshiroRealm=new MyshiroRealm();
        myshiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myshiroRealm;
    }
    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    public ShiroDialect shiroDialect(){return new ShiroDialect();}
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/initPage");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        Map<String,String>filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/favicon.png","anon");
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/font-awesome/**","anon");
        List<Permission>list=permissionService.findAll();
        for (Permission p:list){
            if (!p.getPerurl().isEmpty()){
                String permission="perms["+p.getPerurl()+"]";
                filterChainDefinitionMap.put(p.getPerurl(),permission);
            }
        }
        filterChainDefinitionMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();//设置realm
        securityManager.setRealm(getMyShiroRealm());//自定义缓存实现 使用redis
        securityManager.setSessionManager(securityManager);//自定义session管理。使用redis
        return securityManager;
    }
    @Bean
    public  HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数
        return hashedCredentialsMatcher;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return  authorizationAttributeSourceAdvisor;
    }
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setExpire(1800);//缓存过期时间
        redisManager.setTimeout(timeout);
        return redisManager;
    }
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return  redisCacheManager;
    }
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return  redisSessionDAO;
    }
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());
        return defaultWebSessionManager;
    }


}
