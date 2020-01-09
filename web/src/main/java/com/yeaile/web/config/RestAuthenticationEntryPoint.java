package com.yeaile.web.config;

import cn.hutool.json.JSONUtil;
import com.yeaile.common.result.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2020/1/9
 * @return
 * 当未登录或者token失效访问接口时，自定义的返回结果
 **/
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(RespBean.error(authException.getMessage())));
        response.getWriter().flush();
    }
}
