package com.yeaile.common.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
    private String id;

	/**
	 * 菜单描述
	 */
    private String description;

	/**
	 * 菜单名称
	 */
    private String name;

	/**
	 * 主键
	 */
    private String pid;


    private String path;

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
