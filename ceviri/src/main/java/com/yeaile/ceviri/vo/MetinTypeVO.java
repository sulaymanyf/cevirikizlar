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
 * 回答
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetinTypeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 类型编号
	 */
     private String id;
	/**
	 * 问题ID
	 */
     private String typeName;
	/**
	 * 说明
	 */
     private String typeInfo;
	/**
	 * 父id
	 */
     private String pid;



}
