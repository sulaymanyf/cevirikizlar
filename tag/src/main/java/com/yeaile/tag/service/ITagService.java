package com.yeaile.tag.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.common.domain.tag.dto.TagDTO;
import com.yeaile.common.domain.tag.vo.TagVo;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-27
 */
public interface ITagService {

    TagVo tag(String id);

    IPage<TagVo> listTag();

    void tag(TagDTO tagDTO);

    void UpdateTag(TagDTO tagDTO);

    void deleteTag(String id);
}
