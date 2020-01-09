package com.yeaile.ceviri.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.base.Charsets;
import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.ceviri.entity.EngSoz;
import com.yeaile.ceviri.entity.Metin;
import com.yeaile.ceviri.mapper.CeviriMapper;
import com.yeaile.ceviri.mapper.EngSozlukMapper;
import com.yeaile.ceviri.mapper.MetinMapper;
import com.yeaile.ceviri.service.IEngSozlukService;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.common.constant.MetinSatus;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.EngSozVo;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.file.entity.MyFile;
import com.yeaile.file.mapper.FileMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 原文 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Service
public class EngSozlukServiceImpl implements IEngSozlukService {



    @Resource
    private EngSozlukMapper engSozlukMapper;


    @Override
    public List<String> getWordList(String keyword) {

        List<String> engSozs = engSozlukMapper.selectListByWord(keyword);
        return engSozs;
    }

    @Override
    public EngSozVo getWorTranslate(String keyword) {
        QueryWrapper<EngSoz> query = new QueryWrapper();
        query.eq("word",keyword);
        EngSoz engSoz = engSozlukMapper.selectOne(query);
        EngSozVo engSozVo = BeanUtil.copy(engSoz, EngSozVo.class);
        return engSozVo;
    }
}

