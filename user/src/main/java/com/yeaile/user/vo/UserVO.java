package com.yeaile.user.vo;


import java.io.Serializable;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

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



}
