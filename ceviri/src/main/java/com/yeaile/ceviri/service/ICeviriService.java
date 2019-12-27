package com.yeaile.ceviri.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.common.domain.ceviri.dto.CeviriDTO;
import com.yeaile.common.domain.ceviri.dto.CeviriQueryDTO;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;

/**
 * <p>
 * 原文 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
public interface ICeviriService {

    CeviriVO cevir(String id);

    IPage<CeviriVO> listCeviri(CeviriQueryDTO ceviriQueryDTO);

    void saveCeviri(CeviriDTO ceviriDTO);
}
