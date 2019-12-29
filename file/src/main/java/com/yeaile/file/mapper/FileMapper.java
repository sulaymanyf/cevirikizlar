package com.yeaile.file.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.file.entity.MyFile;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-28
 */
@Mapper
public interface FileMapper extends BaseMapper<MyFile> {

}
