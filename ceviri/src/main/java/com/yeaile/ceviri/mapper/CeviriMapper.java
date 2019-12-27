package com.yeaile.ceviri.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.ceviri.entity.Ceviri;
import com.yeaile.common.domain.ceviri.dto.CeviriQueryDTO;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;
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
public interface CeviriMapper extends BaseMapper<Ceviri> {

    IPage<CeviriVO> findPage(@Param("page") IPage<CeviriVO> page, @Param("queryDto") CeviriQueryDTO ceviriQueryDTO);

    Ceviri selectByMetinId(@Param("metinId") String metinId);
}
