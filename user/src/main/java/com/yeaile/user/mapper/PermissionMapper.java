package com.yeaile.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getAllPermissionWithRole();

}
