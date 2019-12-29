package com.yeaile.common.domain.tag.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 标签
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagQueryDTO implements Serializable {

private static final long serialVersionUID = 1L;


	/**
	 * tag名称
	 */
    private String tagName;

	/**
	 * tag颜色
	 */
    private String tagColor;




}
