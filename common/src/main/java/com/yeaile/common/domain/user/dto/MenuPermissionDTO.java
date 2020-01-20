package com.yeaile.common.domain.user.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigInteger;
import java.math.BigDecimal;
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
public class MenuPermissionDTO implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer id;

    private String menuId;

    private String permissionId;





}
