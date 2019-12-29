package com.yeaile.common.domain.file.vo;


import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * <p>
 * 
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileVO implements Serializable {

	private static final long serialVersionUID = 1L;

     private String id;
     private String fileName;
	/**
	 * 审核状态
	 */
     private Integer state;
	/**
	 * URL
	 */
     private String url;



}
