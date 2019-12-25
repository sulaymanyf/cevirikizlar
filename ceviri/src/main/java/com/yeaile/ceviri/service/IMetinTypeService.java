package com.yeaile.ceviri.service;


import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;

/**
 * <p>
 * 回答 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
public interface IMetinTypeService {

    void addOrUpdateMetinType(MetinTypeDTO metinTypeDTO);

    MetinTypeVO MetinType(String id);

    MetinTypeVO MetinTypeTree();


}
