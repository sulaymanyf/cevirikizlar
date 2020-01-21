package com.yeaile.common.domain.user.dto;


import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RoleDTO implements Serializable {

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
   * 权限
   */
  private String description;
  /**
   * 说明
   */
  private List<String> permissions;
  /**
   * 菜单
   */
  private List<String> menus;


}
