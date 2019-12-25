package com.yeaile.user.vo;


import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * <p>
 * 
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
     private String id;
	/**
	 * 权限描述
	 */
     private String description;
	/**
	 * 权限名称
	 */
     private String perName;
	/**
	 * 权限类型 1为菜单 2为功能 3为API
	 */
     private Integer type;
	/**
	 * 主键
	 */
     private String pid;
	/**
	 * 权限等级，1为通用接口权限，2为需校验接口权限
	 */
     private String level;
	/**
	 * 请求类型
	 */
     private String method;
	/**
	 * 链接
	 */
     private String url;
	/**
	 * 权限代码
	 */
     private String icon;
	/**
	 * 排序
	 */
     private Integer sort;
	/**
	 * 状态
	 */
     private Integer status;
     private String code;



}
