package com.yeaile.web.config;

import com.yeaile.common.constant.PermissionStatus;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.user.entity.Permission;
import com.yeaile.user.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2020/1/10
 * @return
 **/

@Configuration
public class initRequestUrl implements ServletContextAware {

    private static List<initPermission> urls = new ArrayList<initPermission>();
    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    private IPermissionService permissionService;

    public void initUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (RequestMappingInfo info : map.keySet()) {
            String name = info.getName();
            String method = null;
            // 获取url的Set集合，一个方法可能对应多个url
            Set<String> patterns = info.getPatternsCondition().getPatterns();
            Set<RequestMethod> methods = info.getMethodsCondition().getMethods();
            Iterator<RequestMethod> iterator = methods.iterator();
            for (RequestMethod met : methods) {
                method = met.name();
            }

            for (String url : patterns) {
                String[] purl_url = url.split("v1");
                try {
                    if (purl_url.length > 1) {
                        Permission permissionVO = new Permission();
                        Permission permission = new Permission();
                        permissionVO.setUrl(purl_url[1]);
                        permissionVO.setName(name);
                        permissionVO.setMethod(method);
                        permission.setMethod(method);
                        permission.setUrl(purl_url[0]);
                        initPermission initPermission = new initPermission();
                        initPermission.setPUrl(purl_url[0]);
                        initPermission.setPermission(permissionVO);
                        urls.add(initPermission);
                    }
                } catch (Exception e) {
                    System.out.println(url);
                    e.printStackTrace();
                }
            }
        }
        // 查询数据库获取权限列表
        List<Permission>  oldPermissionList = permissionService.getAllPermission();



        List<Permission> initPermissionList = new ArrayList<>();
        Map<String, List<initPermission>> permissionList = urls.stream().collect(Collectors.groupingBy(initPermission::getPUrl));
        for (Map.Entry<String, List<initPermission>> Entry : permissionList.entrySet()) {
            Permission permission = new Permission();
            permission.setUrl(Entry.getKey());
            String name = null;
            for (initPermission initPermission : Entry.getValue()) {
                initPermissionList.add(initPermission.getPermission());
                name = initPermission.getPermission().getName().split("#")[0];
            }
            permission.setName(name);
            initPermissionList.add(permission);
        }
        if (oldPermissionList.size() < initPermissionList.size()) {
            // listA.removeAll(listB)
            // listA.addAll(listB)  B 不变
            // 取差集
            //差集
            List<Permission> distinctByUniqueList = initPermissionList.stream()
                    .filter(item -> !oldPermissionList.stream()
                            .map(e -> e.getUrl()+ ";" + e.getMethod())
                            .collect(Collectors.toList())
                            .contains(item.getUrl()+ ";" +item.getMethod()))
                    .collect(Collectors.toList());
            for (Permission permission : distinctByUniqueList) {
                if (permission.getMethod()!=null){
                    List<Permission> byUserNameAndMethod = permissionService.getAllPathsByUserNameAndMethod(permission.getUrl().split("/")[1]);
                    if (byUserNameAndMethod.size()>0){
                        permission.setPid(byUserNameAndMethod.get(0).getPid());
                    }
                }
            }
            System.out.println(distinctByUniqueList);
            permissionService.addPermission(distinctByUniqueList);
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        initUrl();
    }
}
