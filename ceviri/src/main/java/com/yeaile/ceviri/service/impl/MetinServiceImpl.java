package com.yeaile.ceviri.service.impl;


import com.yeaile.ceviri.mapper.MetinMapper;
import com.yeaile.ceviri.service.IMetinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 原文 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Service
public class MetinServiceImpl implements IMetinService {

    @Autowired
    private MetinMapper metinMapper ;
}
