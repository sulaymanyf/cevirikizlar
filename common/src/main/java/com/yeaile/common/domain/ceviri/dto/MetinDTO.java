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
public class MetinDTO implements Serializable {

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
	 * 标题
	 */
    private String title;

	/**
	 * 文章正文
	 */
    private String content;

	/**
	 * 审核状态
	 */
    private Integer state;

	/**
	 * URL
	 */
    private String url;

	/**
	 * 文章类型
	 */
    private String metinType;


    private String fileId;

    private String mark;







}
