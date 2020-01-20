package com.yeaile.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.yeaile.common.base.entity.BaseEntity;
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
@TableName("tb_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 菜单描述
     */
    @TableField("description")
    private String description;

    /**
     * 菜单名称
     */
    @TableField("name")
    private String name;

    /**
     * 主键
     */
    @TableField("pid")
    private String pid;

    @TableField("path")
    private String path;

    /**
     * 链接
     */
    @TableField("url")
    private String url;

    /**
     * 权限代码
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    @TableField("code")
    private String code;

    @TableField("status")
    private Integer status;
    @TableField("type")
    private Integer type;


    @Tolerate
    public Menu() {
    }


}
