package com.yeaile.ceviri.entity;
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
 * 回答
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_metin_type")
public class MetinType extends BaseEntity {

private static final long serialVersionUID=1L;


        /**
         * 问题ID
         */
        @TableField("type_name")
            private String typeName;

        /**
         * 说明
         */
        @TableField("type_info")
            private String typeInfo;

        /**
         * 父id
         */
        @TableField("pid")
            private String pid;

        @TableField("delete_flag")
            private Integer deleteFlag;



@Tolerate public MetinType(){
    }


    }
