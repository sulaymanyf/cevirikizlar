package com.yeaile.common.domain.user.vo;


import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class PermissionNodeVO {

	private static final long serialVersionUID = 1L;

	@JSONField(serialize = false)
	protected String id;
	@JSONField(serialize = false)
	protected String pid;
	private String title;
	private String value;
	private String key;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<PermissionNodeVO> children = new ArrayList<>();


	public PermissionNodeVO(String id, String pid, String name) {
		this.id = id;
		this.pid = pid;
		this.title = name;
		this.value = id;
		this.key = id;
	}
}
