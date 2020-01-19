package com.yeaile.common.domain.user.vo;


import java.io.Serializable;
import java.util.List;

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
public class RoleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
     private String id;
	/**
	 * 权限名称
	 */
     private String roleName;
	/**
	 * 说明
	 */
     private String description;

     private List<PermissionVO> permissionVOS;

     private List<MenuVO> menuVOS;



}
