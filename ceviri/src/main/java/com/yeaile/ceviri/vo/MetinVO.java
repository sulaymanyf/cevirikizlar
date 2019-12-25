package com.yeaile.ceviri.vo;


import java.io.Serializable;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
     private String state;
	/**
	 * URL
	 */
     private String url;
	/**
	 * 文章类型
	 */
     private String metinType;



}
