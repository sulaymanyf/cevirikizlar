package com.yeaile.ceviri.service;


import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeNodeVO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;
import com.yeaile.common.domain.tag.vo.TagVo;
import java.util.List;

/**
 * <p>
 * 回答 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
public interface IMetinTypeService {

  List<MetinTypeVO> addOrUpdateMetinType(MetinTypeDTO metinTypeDTO);

  MetinTypeVO MetinType(String id);

  List<MetinTypeNodeVO> MetinTypeTree();


  List<MetinTypeVO>  tipList();


  List<MetinTypeVO> delete(String id);
}
