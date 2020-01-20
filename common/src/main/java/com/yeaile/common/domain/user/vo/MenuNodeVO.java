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
public class MenuNodeVO {

	private static final long serialVersionUID = 1L;

	@JSONField(serialize = false)
	protected String id;
	@JSONField(serialize = false)
	protected String pid;
	private String name;
	private String icon;
	private String path;
	private Integer sort;
	private String code;
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	protected List<MenuNodeVO> children = new ArrayList<>();


	public MenuNodeVO(String id, String pid, String name, String icon, String path,Integer sort,String code) {
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.icon = icon;
		this.sort = sort;
		this.code = code;
		this.path = path;
	}
}
