package com.yeaile.ceviri.entity;

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
 * 原文
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("tb_metin")
public class Metin extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 文章正文
     */
    @TableField("content")
    private String content;

    /**
     * 审核状态
     */
    @TableField("status")
    private Integer status;

    /**
     * URL
     */
    @TableField("url")
    private String url;

    /**
     * 文章类型
     */
    @TableField("metin_type")
    private String metinType;

    @TableField("language")
    private Integer language;

    @TableField("to_language")
    private Integer toLanguage;

    @TableField("tag_id")
    private Integer tagId;

    /**
     * 文章备注
     */
    @TableField("mark")
    private String mark;

    /**
     * 文章备注
     */
    @TableField("price")
    private String price;


    @Tolerate
    public Metin() {
    }


}
