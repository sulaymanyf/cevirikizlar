package com.yeaile.web.module.user.dto.user.domain;


import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

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
public class   RoleDTO implements Serializable {

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

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    private Integer deleteFlag;





}
