package com.yeaile.ceviri.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.EngSozVo;
import com.yeaile.common.domain.ceviri.vo.MetinVO;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 原文 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
public interface IEngSozlukService {


    List<String> getWordList(String keyword);

    EngSozVo getWorTranslate(String keyword);
}
