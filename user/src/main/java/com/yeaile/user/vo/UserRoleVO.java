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
public class UserRoleVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 角色ID
	 */
     private String roleId;
	/**
	 * 权限ID
	 */
     private String userId;



}
