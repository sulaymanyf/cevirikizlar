package com.yeaile.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.user.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    void deleteByRoleId(@Param("id") String id);

}
