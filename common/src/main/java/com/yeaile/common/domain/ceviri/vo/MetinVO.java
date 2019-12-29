package com.yeaile.common.domain.ceviri.vo;


import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

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
public class MetinVO implements Serializable {

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
	 * 用户ID
	 */
	private String userName;
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
     private Integer status;
	/**
	 * URL
	 */
     private String url;
	/**
	 * 文章类型
	 */
     private String metinType;

	/**
	 * 文章类型name
	 */
	private String typeName;
	/**
	 * 文章类备注
	 */
	private String mark;



	private String fileType;




}
