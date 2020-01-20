package com.yeaile.common.domain.user.vo;


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
 * @since 2020-01-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuVO implements Serializable {

	private static final long serialVersionUID = 1L;

     private Integer id;
     private String roleId;
     private String menuId;



}
