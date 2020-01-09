package com.yeaile.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.user.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> selectByUserId(@Param("id") String id);
}
