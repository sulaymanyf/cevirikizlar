package com.yeaile.common.domain.ceviri.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.yeaile.common.utils.conn.BaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 回答
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Data
@NoArgsConstructor
public class MetinTypeNodeVO  {

	private static final long serialVersionUID = 1L;


	protected String id;
	@JSONField(serialize = false)
	protected String pid;
	private String value;
	private String label;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<MetinTypeNodeVO> children = new ArrayList<>();


	public MetinTypeNodeVO(String id, String pid, String value, String label) {
		this.id = id;
		this.pid = pid;
		this.value = value;
		this.label = label;
	}
}
