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
public class UserRoleDTO implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
    private String roleId;

	/**
	 * 权限ID
	 */
    private String userId;

	/**
	 * 修改日期
	 */
    private LocalDateTime createTime;

	/**
	 * 修改日期
	 */
    private LocalDateTime updateTime;

    private Integer deleteFlag;





}
