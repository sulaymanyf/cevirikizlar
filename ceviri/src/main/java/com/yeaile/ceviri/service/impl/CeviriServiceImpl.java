package com.yeaile.ceviri.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.ceviri.mapper.CeviriMapper;
import com.yeaile.ceviri.service.ICeviriService;
import com.yeaile.common.constant.MetinSatus;
import com.yeaile.common.domain.ceviri.dto.CeviriDTO;
import com.yeaile.common.domain.ceviri.dto.CeviriQueryDTO;
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

    @Override
    public IPage<CeviriVO> listCeviri(CeviriQueryDTO ceviriQueryDTO) {
        IPage<CeviriVO> page = new Page<>();
        IPage<CeviriVO> voiPage= ceviriMapper.findPage(page,ceviriQueryDTO);
        return voiPage;
    }

    @Override
    public void saveCeviri(CeviriDTO ceviriDTO) {
        Ceviri ceviri =  ceviriMapper.selectByMetinId(ceviriDTO.getMetinId());
        if (ceviri.getState()== MetinSatus.END.getCode()){
            // 抛异常
        }
        Ceviri newCeviri = BeanUtil.copy(ceviriDTO, Ceviri.class);
        newCeviri.setId(ceviri.getId());
        ceviriMapper.updateById(newCeviri);

    }
}
