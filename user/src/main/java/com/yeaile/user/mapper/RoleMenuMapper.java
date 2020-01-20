package com.yeaile.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeaile.common.domain.user.vo.MenuVO;
import com.yeaile.user.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<MenuVO> selectMenuByRoleId(@Param("id") String id);
}
