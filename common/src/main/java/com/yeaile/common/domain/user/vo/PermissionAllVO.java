package com.yeaile.common.domain.user.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 
 * </p>3
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionAllVO implements Serializable {

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
	 * 菜单名称
	 */
     private String menuName;

	/**
	 * 权限代码
	 */
     private String icon;
	/**
	 * 排序
	 */
     private Integer sort;

	private String url;

	/**
	 * 菜单名称
	 */
	private List<String> perName;

	private List<PermissionVO> permissionVOS;
}
