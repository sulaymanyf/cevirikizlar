package com.yeaile.web.config;

import org.apache.ibatis.javassist.tools.web.Webserver;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/23
 * @return
 **/
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");

    }
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        for (String url : ignoreUrlsConfig().getUrls()) {
//            web.ignoring().antMatchers(url);
//        }
//    }
////    @Bean
////    @Override
////    public AuthenticationManager authenticationManagerBean() throws Exception {
////        AuthenticationManager manager = super.authenticationManagerBean();
////        return manager;
////    }
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
//        return new JwtAuthenticationTokenFilter();
//    }
//
//
//    //采用bcrypt对密码进行编码
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }



//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        //关闭跨站请求防护
//        http.csrf().disable()
//                // 登录行为由自己实现，参考 LoginController#login
//                .formLogin().disable()
//                .httpBasic()
//                .and()
//                .formLogin()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                // RBAC 动态 url 认证
//                .anyRequest()
//                .access("@rbacAuthorityService.hasPermission(request,authentication)")
//                .and().logout().disable()
//                // 不使用session
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                // 自定义权限拒绝处理类
//                .and()
//                // 异常处理
//                .exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler())
//                .authenticationEntryPoint(restAuthenticationEntryPoint())
//                .and()
//                // 自定义权限拦截器JWT过滤器
//                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService())
//                .passwordEncoder(passwordEncoder());
//    }
//
//
//    @Bean
//    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
//        return new RestfulAccessDeniedHandler();
//    }
//
//    @Bean
//    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
//        return new RestAuthenticationEntryPoint();
//    }
//
//    @Bean
//    public IgnoreUrlsConfig ignoreUrlsConfig() {
//        return new IgnoreUrlsConfig();
//    }
//
//    @Bean
//    public JwtTokenUtil jwtTokenUtil() {
//        return new JwtTokenUtil();
//    }

    /**
     /**
     * 放行所有不需要登录就可以访问的请求，参见 AuthController
     * 也可以在 {@link #configure(HttpSecurity)} 中配置
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}

    @Override
    public void configure(WebSecurity web) {
        WebSecurity and = web.ignoring().and();

        // 忽略 GET
        customConfig.getIgnores().getGet().forEach(url -> and.ignoring().antMatchers(HttpMethod.GET, url));

        // 忽略 POST
        customConfig.getIgnores().getPost().forEach(url -> and.ignoring().antMatchers(HttpMethod.POST, url));

        // 忽略 DELETE
        customConfig.getIgnores().getDelete().forEach(url -> and.ignoring().antMatchers(HttpMethod.DELETE, url));

        // 忽略 PUT
        customConfig.getIgnores().getPut().forEach(url -> and.ignoring().antMatchers(HttpMethod.PUT, url));

        // 忽略 HEAD
        customConfig.getIgnores().getHead().forEach(url -> and.ignoring().antMatchers(HttpMethod.HEAD, url));

        // 忽略 PATCH
        customConfig.getIgnores().getPatch().forEach(url -> and.ignoring().antMatchers(HttpMethod.PATCH, url));

        // 忽略 OPTIONS
        customConfig.getIgnores().getOptions().forEach(url -> and.ignoring().antMatchers(HttpMethod.OPTIONS, url));

        // 忽略 TRACE
        customConfig.getIgnores().getTrace().forEach(url -> and.ignoring().antMatchers(HttpMethod.TRACE, url));

        // 按照请求格式忽略
        customConfig.getIgnores().getPattern().forEach(url -> and.ignoring().antMatchers(url));
    }
     */

}

