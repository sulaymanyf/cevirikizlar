package com.yeaile.user.service;


import com.yeaile.common.domain.user.dto.RoleDTO;
import com.yeaile.common.domain.user.vo.RoleVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
public interface IRoleService {

    void addUser(RoleDTO roleDTO);

    List<RoleVO> getRoleList(String roleId);


}
