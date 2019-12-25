package com.yeaile.ceviri.service.impl;


import com.yeaile.ceviri.entity.MetinType;
import com.yeaile.ceviri.mapper.MetinTypeMapper;
import com.yeaile.ceviri.service.IMetinTypeService;
import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


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

    @Resource
    private MetinTypeMapper metinTypeMapper ;

    @Override
    public void addOrUpdateMetinType(MetinTypeDTO metinTypeDTO) {
        MetinType metinType = BeanUtil.copy(metinTypeDTO, MetinType.class);
        if (metinType.getId() ==null){
            //新增
            metinType.setId(IdWorkerUtil.getIdStr());
            metinTypeMapper.insert(metinType);
        }else {
            MetinType selectById = metinTypeMapper.selectById(metinTypeDTO.getId());
            metinType.setId(selectById.getId());
            metinTypeMapper.updateById(metinType);
        }
    }

    @Override
    public MetinTypeVO MetinType(String id) {
        MetinType metinType = metinTypeMapper.selectById(id);
        return BeanUtil.copy(metinType,MetinTypeVO.class);
    }

    @Override
    public MetinTypeVO MetinTypeTree() {
        return null;
    }
}
