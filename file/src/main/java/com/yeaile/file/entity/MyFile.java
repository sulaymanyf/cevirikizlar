package com.yeaile.file.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.yeaile.common.base.entity.BaseEntity;

import lombok.*;
import lombok.experimental.Tolerate;

/**
 * <p>
 *
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_file")
public class MyFile extends BaseEntity {

    private static final long serialVersionUID = 1L;


    @TableField("file_name")
    private String fileName;

    /**
     * 审核状态
     */
    @TableField("state")
    private Integer state;

    /**
     * URL
     */
    @TableField("url")
    private String url;
    /**
     * URL
     */
    @TableField("path")
    private String path;

    /**
     * URL
     */
    @TableField("suffix")
    private String suffix;


    @Tolerate
    public MyFile() {
    }


}
