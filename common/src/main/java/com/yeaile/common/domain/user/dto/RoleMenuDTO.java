package com.yeaile.common.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class RoleMenuDTO implements Serializable {

private static final long serialVersionUID = 1L;

    private Integer id;

    private String menuId;

    private String RoleId;





}
