package com.yeaile.ceviri.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.ceviri.entity.EngSoz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 回答 Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */
@Mapper
public interface EngSozlukMapper extends BaseMapper<EngSoz> {


    List<String> selectListByWord(@Param("keyword") String keyword);
}
