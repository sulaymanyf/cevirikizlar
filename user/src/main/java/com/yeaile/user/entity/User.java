package com.yeaile.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yeaile.common.base.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_user")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 用户姓名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("sex")
    private String sex;

    /**
     * 姓
     */
    @TableField("first_name")
    private String firstName;

    /**
     * 名
     */
    @TableField("last_name")
    private String lastName;

    /**
     * 出生年月日
     */
    @TableField("birthday")
    private LocalDateTime birthday;

    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * E-Mail
     */
    @TableField("email")
    private String email;


    @TableField("user_status")
    private Integer userStatus;

    /**
     * 注册日期
     */
    @TableField("regdate")
    private LocalDateTime regdate;

    /**
     * 在线时长（分钟）
     */
    @TableField("online")
    private BigInteger online;

    /**
     * 兴趣
     */
    @TableField("interest")
    private String interest;

    /**
     * 登录次数
     */
    @TableField("login_count")
    private BigInteger loginCount;

    /**
     * 个性
     */
    @TableField("personality")
    private String personality;

    /**
     * 粉丝数
     */
    @TableField("fanscount")
    private Integer fanscount;

    /**
     * 关注数
     */
    @TableField("followcount")
    private Integer followcount;


    @Tolerate
    public User() {
    }


}
