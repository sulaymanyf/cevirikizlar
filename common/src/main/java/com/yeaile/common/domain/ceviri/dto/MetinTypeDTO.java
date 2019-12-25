package com.yeaile.common.domain.ceviri.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class MetinTypeDTO implements Serializable {

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

	/**
	 * 修改日期
	 */
    private LocalDateTime createTime;

	/**
	 * 修改日期
	 */
    private LocalDateTime updateTime;

    private Integer deleteFlag;





}
