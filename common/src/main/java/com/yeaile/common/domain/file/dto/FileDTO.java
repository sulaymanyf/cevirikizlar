package com.yeaile.common.domain.file.dto;



import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class FileDTO implements Serializable {

private static final long serialVersionUID = 1L;

    private String id;

    private String fileName;

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
    private Integer state;

	/**
	 * URL
	 */
    private String url;






}
