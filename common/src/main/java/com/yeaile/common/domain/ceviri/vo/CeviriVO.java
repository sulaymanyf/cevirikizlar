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
public class CeviriVO implements Serializable {

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
	 * 审核状态
	 */
     private String state;
	/**
	 * URL
	 */
     private String url;

     private String cevirFileId;


}
