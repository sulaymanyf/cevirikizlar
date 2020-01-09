package com.yeaile.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.user.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    Role selectByName(@Param("rolename") String rolename);
}
