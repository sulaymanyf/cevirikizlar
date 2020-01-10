package com.yeaile.web.config;

import cn.hutool.system.UserInfo;
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
                if (purl_url.length > 1) {
                    Permission permissionVO = new Permission();
                    permissionVO.setPerName(name);
                    permissionVO.setUrl(purl_url[1]);
                    permissionVO.setMethod(method);
                    initPermission initPermission = new initPermission();
                    initPermission.setPUrl(purl_url[0]);
                    initPermission.setPermission(permissionVO);
                    urls.add(initPermission);
                }
            }
        }
        HashMap<String, List<Permission>> stringPermissionHashMap = new HashMap<>();

        ArrayList<Permission> permissions = new ArrayList<>();

        Set<initPermission> personSet = new TreeSet<initPermission>((o1, o2) -> o1.getPUrl().compareTo(o2.getPUrl()));
        personSet.addAll(urls);
        ArrayList<initPermission> initPermissions = new ArrayList<>(personSet);
//        List<Permission> permissionList = urls.stream().collect(Collectors
//                .collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(o -> {
//                    // 根据useId和userName进行去重
//                    return o.getPermission();
//                }))), ArrayList::new));


//        for (initPermission initPermission : urls) {
//            stringPermissionHashMap.put(initPermission.getPUrl(),initPermission.getPermission());
//        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        initUrl();
    }
}
