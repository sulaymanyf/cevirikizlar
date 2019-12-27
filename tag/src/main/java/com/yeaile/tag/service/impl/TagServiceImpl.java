package com.yeaile.tag.service.impl;

import com.yeaile.tag.service.ITagService;
import com.yeaile.tag.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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

    @Autowired
    private TagMapper tagMapper ;
}
