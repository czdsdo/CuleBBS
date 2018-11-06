package com.example.admin.shiro;

import com.example.admin.service.PermissionService;
import com.example.common.entity.Permission;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShiroService {
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisSessionDAO redisSessionDAO;
    /**
     * 初始化权限
     */
    public Map<String,String> loadFilterChainDefinitions(){
        Map<String,String>filterChainDefinitionMap=new LinkedHashMap<>();
        filterChainDefinitionMap.put("/logout","logout");
        filterChainDefinitionMap.put("/css/**","anon");
        filterChainDefinitionMap.put("/js/**","anon");
        filterChainDefinitionMap.put("/img/**","anon");
        filterChainDefinitionMap.put("/font-awesome/**","anon");
        List<Permission>permissionList=permissionService.findAll();
        for (Permission p:permissionList){
            if (!StringUtils.isEmpty(p.getPerurl())){
                String permission="perms["+p.getPerurl()+"]";
                filterChainDefinitionMap.put(p.getPerurl(),permission);
            }
        }
        filterChainDefinitionMap.put("/**","authc");
        return  filterChainDefinitionMap;
    }
    /**
     * 重新加载权限
     */
    public void updatePermission(){
        AbstractShiroFilter shiroFilter=null;
        try {
            shiroFilter=(AbstractShiroFilter)shiroFilterFactoryBean.getObject();
        }catch (Exception e){
            throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
        }
        PathMatchingFilterChainResolver filterChainResolver=(PathMatchingFilterChainResolver)shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager=(DefaultFilterChainManager)filterChainResolver.getFilterChainManager();
        manager.getFilterChains().clear();//清空老的权限控制
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
        Map<String,String>chains=shiroFilterFactoryBean.getFilterChainDefinitionMap();
        for (Map.Entry<String,String>entry:chains.entrySet()){
            String url=entry.getKey();
            String chainDefinition=entry.getValue().trim().replace(" ","");
            manager.createChain(url,chainDefinition);
        }
    }
}
