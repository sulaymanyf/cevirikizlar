package com.yeaile.ceviri.service.impl;

import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.ceviri.mapper.CeviriMapper;
import com.yeaile.ceviri.service.ICeviriService;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;
import com.yeaile.common.utils.BeanUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * 原文 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Service
public class CeviriServiceImpl implements ICeviriService {

    @Resource
    private CeviriMapper ceviriMapper ;

    @Override
    public CeviriVO cevir(String id) {

        Ceviri ceviri = ceviriMapper.selectById(id);
        CeviriVO ceviriVO = BeanUtil.copy(ceviri, CeviriVO.class);
        return ceviriVO;
    }
}
