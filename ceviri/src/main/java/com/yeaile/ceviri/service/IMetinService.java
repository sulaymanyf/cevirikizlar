package com.yeaile.ceviri.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;

import java.io.IOException;

/**
 * <p>
 * 原文 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
public interface IMetinService {

    MetinVO metin(String id);

    void addOrUpdateMetin(MetinDTO metinDTO);

    IPage<MetinVO> listMetin(int current, int pageSize, MetinQueryDTO metinDTO);

    void addMetin(MetinDTO metinDTO) throws IOException;

}
