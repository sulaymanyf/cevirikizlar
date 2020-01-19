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
 * @since 2019-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_permission")
public class Permission extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 权限描述
     */
    @TableField("description")
    private String description;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限类型 1为菜单 2为功能 3为API
     */
    @TableField("type")
    private Integer type;

    /**
     * 主键
     */
    @TableField("pid")
    private String pid;

    /**
     * 权限等级，1为通用接口权限，2为需校验接口权限
     */
    @TableField("level")
    private String level;

    /**
     * 请求类型
     */
    @TableField("method")
    private String method;

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

    @TableField("path")
    private String path;

    @Tolerate
    public Permission() {
    }


}
