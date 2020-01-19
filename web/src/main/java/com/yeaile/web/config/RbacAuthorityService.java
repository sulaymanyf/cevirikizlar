package com.yeaile.web.config;

import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.user.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Classname RbacAuthorityService
 * @Description TODO
 * @Date 2020/1/9 10:15 下午
 * @Created by SulaymanYf
 */
@Component("rbacauthorityservice")
public class RbacAuthorityService {

    @Autowired
    private IPermissionService iPermissionService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object userInfo = authentication.getPrincipal();

        boolean hasPermission  = false;

        if (userInfo instanceof UserDetails) {

            String username = ((UserDetails) userInfo).getUsername();

            //获取资源
            Set<String> urls = new HashSet();
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) userInfo).getAuthorities();
            Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
            for (GrantedAuthority authority : authorities) {
                if ("ROLE_admin".equalsIgnoreCase(authority.getAuthority())){
                    hasPermission = true;
                    return hasPermission;
                }
            }
            List<String> perUrls =  iPermissionService.getAllPathsByUserName(username);
            for (String pers : perUrls) {
                urls.add(pers);
            }
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }
}
