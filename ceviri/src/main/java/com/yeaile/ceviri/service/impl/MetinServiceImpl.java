package com.yeaile.ceviri.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.ceviri.entity.Metin;
import com.yeaile.ceviri.mapper.CeviriMapper;
import com.yeaile.ceviri.mapper.MetinMapper;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.common.constant.MetinSatus;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorker;
import com.yeaile.common.utils.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MetinServiceImpl implements IMetinService {

    @Resource
    private MetinMapper metinMapper ;

    @Resource
    private CeviriMapper ceviriMapper;



    @Override
    public MetinVO metin(String id) {
        Metin metin = metinMapper.selectById(id);
        MetinVO metinVO = BeanUtil.copy(metin, MetinVO.class);
        return metinVO;
    }

    @Override
    public void addOrUpdateMetin(MetinDTO metinDTO) {

        Metin metin = BeanUtil.copy(metinDTO, Metin.class);
        if (metinDTO.getId()==null){
            // 新增
            // 添加原文

            String id = IdWorkerUtil.getIdStr();
            metin.setId(id);
            metin.setStatus(MetinSatus.NEW.getCode());
            metinMapper.insert(metin);


            // 生成译文
            Ceviri ceviri = new Ceviri();
            ceviri.setId(IdWorkerUtil.getIdStr());
            ceviri.setMetinId(id);
            ceviri.setState(MetinSatus.NEW.getCode());
            ceviriMapper.insert(ceviri);
        }else{
            //修改

            Metin metinOld = metinMapper.selectById(metinDTO.getId());
            if (metinOld.getStatus() == MetinSatus.END.getCode() || metinOld.getStatus() == MetinSatus.TRANSLATING.getCode()){
                // 抛异常
                System.out.println("sssss");
            }
            metin.setId(metinOld.getId());
            metinMapper.updateById(metin);

        }



    }

    @Override
    public IPage<MetinVO> listMetin(MetinQueryDTO metinDTO) {
        IPage<MetinVO> page = new Page<>();
        IPage<MetinVO> voPage = metinMapper.pageSelect(page,metinDTO);
        return voPage;
    }
}
