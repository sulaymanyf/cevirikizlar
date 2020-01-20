package com.yeaile.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Tolerate;

/**
 * <p>
 *
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_menu_permission")
public class MenuPermission {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("menu_id")
    private String menuId;

    @TableField("permission_id")
    private String permissionId;


    @Tolerate
    public MenuPermission() {
    }


}
