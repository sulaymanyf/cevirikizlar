package com.yeaile.common.domain.tag.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagVo implements Serializable {

    private static final long serialVersionUID = -7806275639751922635L;

    /**
     * ID
     */
    private String id;

    /**
     * tag名称
     */
    private String tagName;

    /**
     * tag颜色
     */
    private String tagColor;

}
