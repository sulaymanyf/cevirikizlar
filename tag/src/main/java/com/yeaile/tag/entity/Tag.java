package com.yeaile.tag.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;


import com.yeaile.common.base.entity.BaseEntity;
import lombok.*;
import lombok.experimental.Tolerate;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_tag")
public class Tag extends BaseEntity {

private static final long serialVersionUID=1L;


        /**
         * tag名称
         */
        @TableField("tag_name")
            private String tagName;

        /**
         * tag颜色
         */
        @TableField("tag_color")
            private String tagColor;




@Tolerate public Tag(){
    }


    }
