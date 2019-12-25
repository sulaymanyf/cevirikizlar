package com.yeaile.common.domain.ceviri.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 原文
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CeviriDTO implements Serializable {

private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
    private String id;

	/**
	 * 用户ID
	 */
    private String userId;

	/**
	 * 原文id
	 */
    private String metinId;

	/**
	 * 译文标题
	 */
    private String title;

	/**
	 * 译文正文
	 */
    private String content;

	/**
	 * 创建日期
	 */
    private LocalDateTime createTime;

	/**
	 * 修改日期
	 */
    private LocalDateTime updateTime;

	/**
	 * 审核状态
	 */
    private String state;

	/**
	 * URL
	 */
    private String url;

    private Integer deleteFlag;





}
