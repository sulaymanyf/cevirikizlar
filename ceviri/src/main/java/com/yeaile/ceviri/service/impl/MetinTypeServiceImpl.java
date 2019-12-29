package com.yeaile.ceviri.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeaile.ceviri.entity.MetinType;
import com.yeaile.ceviri.mapper.MetinTypeMapper;
import com.yeaile.ceviri.service.IMetinTypeService;
import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeNodeVO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.management.Query;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
            metinType.setPid("0");
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
    public List<MetinTypeNodeVO> MetinTypeTree() {
        Wrapper<MetinType> query = new QueryWrapper<>();
        List<MetinType> metinTypes = metinTypeMapper.selectList(query);
        ArrayList<MetinTypeNodeVO> metinTypeNodeVOS = new ArrayList<>();
        for (MetinType metinType : metinTypes) {
            if (metinType!=null){
                metinTypeNodeVOS.add(new MetinTypeNodeVO(metinType.getId(),metinType.getPid(),metinType.getId(),metinType.getTypeName()));
            }
        }
        List<MetinTypeNodeVO> metinTree = ListToTree(metinTypeNodeVOS);
        return metinTree;
    }


    public static List<MetinTypeNodeVO> ListToTree(List<MetinTypeNodeVO> list){

        Map<String, MetinTypeNodeVO> mapTmp = new HashMap<>();
        for (MetinTypeNodeVO current : list) {
            mapTmp.put(current.getId(), current);
        }
        System.out.println(mapTmp);
        List<MetinTypeNodeVO> finalList = new ArrayList<>();

        mapTmp.forEach((k, v) -> {
            if("".equals(v.getPid())|| "0".equals(v.getPid())) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getPid()).getChildren().add(v);
            }
        });
        return finalList;
    }

}
