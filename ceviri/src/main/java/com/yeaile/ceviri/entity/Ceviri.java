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
@TableName("tb_ceviri")
public class Ceviri extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 原文id
     */
    @TableField("metin_id")
    private String metinId;

    /**
     * 译文标题
     */
    @TableField("title")
    private String title;

    /**
     * 译文正文
     */
    @TableField("content")
    private String content;

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


    @TableField("language")
    private Integer language;


    @Tolerate
    public Ceviri() {
    }


}
