package com.yeaile.ceviri.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.ceviri.entity.Metin;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 原文 Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Mapper
public interface MetinMapper extends BaseMapper<Metin> {

    IPage<MetinVO> pageSelect(@Param("page") IPage<MetinVO> page, @Param("queryDto") MetinQueryDTO metinDTO);

}
