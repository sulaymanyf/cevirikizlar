package com.yeaile.common.domain.user.vo;


import com.yeaile.common.utils.CollectionUtil;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
@Data
@Builder
@ToString
public class UserAndRoleVo  implements UserDetails, Serializable {
    private static final long serialVersionUID = -62051102850076259L;


    /**
     * ID
     */
    private String id;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 姓
     */
    private String firstName;

    /**
     * 名
     */
    private String lastName;

    /**
     * 出生年月日
     */
    private LocalDateTime birthday;

    private LocalDateTime lastLoginTime;

    /**
     * 头像
     */
    private String avatar;

    /**
     * E-Mail
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改日期
     */
    private LocalDateTime updateTime;

    private Integer deleteFlag;

    private Integer userStatus;

    /**
     * 注册日期
     */
    private LocalDateTime regdate;

    /**
     * 在线时长（分钟）
     */
    private BigInteger online;

    /**
     * 兴趣
     */
    private String interest;

    /**
     * 登录次数
     */
    private BigInteger loginCount;

    /**
     * 个性
     */
    private String personality;

    /**
     * 粉丝数
     */
    private Integer fanscount;

    /**
     * 关注数
     */
    private Integer followcount;

    private List<RoleVO> roles;

    @Tolerate
    public UserAndRoleVo() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>(roles.size());
        for (RoleVO role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
