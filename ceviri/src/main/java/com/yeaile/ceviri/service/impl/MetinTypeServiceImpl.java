package com.yeaile.ceviri.service.impl;


import com.yeaile.ceviri.mapper.MetinTypeMapper;
import com.yeaile.ceviri.service.IMetinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>
 * 回答 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Service
public class MetinTypeServiceImpl implements IMetinTypeService {

    @Autowired
    private MetinTypeMapper metinTypeMapper ;
}
