package com.yeaile.web.module;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yefan
 * @Classname baseController
 * @Description TODO
 * @Date 2020/1/18 6:05 下午
 * @Created by SulaymanYf
 */
public class BaseController {
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String UserId;
    protected Claims claims;

    @ModelAttribute
    public void SetResAndReq(HttpServletRequest request, HttpServletResponse response){
        this.request=request;
        this.response=response;

        Object obj = request.getAttribute("user_claims");
        if (obj!=null){
            this.claims= (Claims) obj;
            this.UserId= (String) claims.get("userId");
        }
    }
}
