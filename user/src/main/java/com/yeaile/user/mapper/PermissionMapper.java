package com.yeaile.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.common.domain.user.vo.PermissionAllVO;
import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.user.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    List<String> getAllPathsByUserName(@Param("username") String username);

    List<Permission> findAll();

    Permission findPermissionByUrl(@Param("url") String url);

    List<Permission> getAllPathsByUserNameAndMethod(@Param("url") String url);

    String getPidByUrl(@Param("url") String url);

    List<Permission> getMainMenu();

    List<PermissionAllVO> selectListAll();

    List<String> getSonPermis(@Param("key") String key);

    List<PermissionVO> selectByPid(@Param("id") String id);

    List<PermissionVO> getUserMenuByUserId(@Param("userId") String userId);

    List<PermissionVO> selectPermissionByRoleId(@Param("id") String id);
}
