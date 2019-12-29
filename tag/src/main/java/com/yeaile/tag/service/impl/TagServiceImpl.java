package com.yeaile.tag.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.common.domain.tag.dto.TagDTO;
import com.yeaile.common.domain.tag.vo.TagVo;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.tag.entity.Tag;
import com.yeaile.tag.service.ITagService;
import com.yeaile.tag.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-27
 */
@Service
public class TagServiceImpl implements ITagService {

    @Resource
    private TagMapper tagMapper ;

    @Override
    public TagVo tag(String id) {
        Tag tag = tagMapper.selectById(id);

        return BeanUtil.copy(tag,TagVo.class);
    }

    @Override
    public IPage<TagVo> listTag() {
        IPage<Tag> page = new Page<>();
        QueryWrapper<Tag> query = new QueryWrapper<>();
        IPage<Tag> tagIPage = tagMapper.selectPage(page, query);
        return BeanUtil.copyPagInfo(tagIPage,TagVo.class);
    }

    @Override
    public void tag(TagDTO tagDTO) {
        Tag tag = BeanUtil.copy(tagDTO, Tag.class);
        tag.setId(IdWorkerUtil.getIdStr());
        tagMapper.insert(tag);
    }

    @Override
    public void UpdateTag(TagDTO tagDTO) {
        Tag tag = BeanUtil.copy(tagDTO, Tag.class);
        tagMapper.updateById(tag);
    }

    @Override
    public void deleteTag(String id) {
        tagMapper.deleteById(id);
    }
}
