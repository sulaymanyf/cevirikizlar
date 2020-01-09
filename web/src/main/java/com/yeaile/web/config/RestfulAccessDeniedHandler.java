package com.yeaile.web.config;

import cn.hutool.json.JSONUtil;
import com.yeaile.common.result.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2020/1/9
 * @return
 * * 当访问接口没有权限时，自定义的返回结果
 **/
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(RespBean.error(e.getMessage())));
        response.getWriter().flush();
    }
}
