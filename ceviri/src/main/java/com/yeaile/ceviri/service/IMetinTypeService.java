package com.yeaile.ceviri.service;


import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeNodeVO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;

import javax.swing.tree.TreeNode;
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

    void addOrUpdateMetinType(MetinTypeDTO metinTypeDTO);

    MetinTypeVO MetinType(String id);

    List<MetinTypeNodeVO> MetinTypeTree();


}
