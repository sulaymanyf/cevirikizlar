package com.yeaile.user.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.yeaile.common.base.entity.BaseEntity;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.*;
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
@TableName("tb_role")
public class Role extends BaseEntity {

private static final long serialVersionUID=1L;


        /**
         * 权限名称
         */
        @TableField("role_name")
            private String roleName;

        /**
         * 说明
         */
        @TableField("description")
            private String description;

        @TableField("delete_flag")
            private Integer deleteFlag;



@Tolerate public Role(){
    }


    }
