package com.yeaile.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeaile.common.domain.user.dto.RoleDTO;
import com.yeaile.common.domain.user.vo.MenuVO;
import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.common.domain.user.vo.RoleVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.CollectionUtil;
import com.yeaile.user.entity.Permission;
import com.yeaile.user.entity.Role;
import com.yeaile.user.entity.RoleMenu;
import com.yeaile.user.entity.RolePermission;
import com.yeaile.user.mapper.PermissionMapper;
import com.yeaile.user.mapper.RoleMapper;
import com.yeaile.user.mapper.RoleMenuMapper;
import com.yeaile.user.mapper.RolePermissionMapper;
import com.yeaile.user.service.IRoleService;
import java.beans.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<RoleVO> addOrUpdate(RoleDTO roleDTO) {
        Role role = BeanUtil.copy(roleDTO, Role.class);
       if (roleDTO.getId()==null){
           //新增
           role.setRoleName("ROLE_"+role.getRoleName());
           roleMapper.insert(role);
           if (CollectionUtil.isNotEmpty(roleDTO.getMenus())){
               for (String menu : roleDTO.getMenus()) {
                   RoleMenu roleMenu = new RoleMenu();
                   roleMenu.setMenuId(menu);
                   roleMenu.setRoleId(role.getId());
                   roleMenuMapper.insert(roleMenu);
               }
           }
           if (CollectionUtil.isNotEmpty(roleDTO.getPermissions())){
               for (String permission : roleDTO.getPermissions()) {
                   RolePermission rolePerm = new RolePermission();
                   rolePerm.setPermissionId(permission);
                   rolePerm.setRoleId(role.getId());
                   rolePermissionMapper.insert(rolePerm);
               }
           }
       }else {
           roleMapper.updateById(role);
           if (CollectionUtil.isNotEmpty(roleDTO.getMenus())){
               roleMenuMapper.deleteByRoleId(role.getId());
               for (String menu : roleDTO.getMenus()) {
                   RoleMenu roleMenu = new RoleMenu();
                   roleMenu.setMenuId(menu);
                   roleMenu.setRoleId(role.getId());
                   roleMenuMapper.insert(roleMenu);
               }
           }
           if (CollectionUtil.isNotEmpty(roleDTO.getPermissions())){
               rolePermissionMapper.deleteByRoleId(role.getId());
               for (String menu : roleDTO.getPermissions()) {
                   RolePermission rolePerm = new RolePermission();
                   rolePerm.setPermissionId(menu);
                   rolePerm.setRoleId(role.getId());
                   rolePermissionMapper.insert(rolePerm);
               }
           }
       }
        return this.getRoleList(null);
    }

    @Override
    public List<RoleVO> getRoleList(String roleId) {
        QueryWrapper qurey = new QueryWrapper();
        qurey.eq(roleId!=null,"id",roleId);
        List<Role> roleList = roleMapper.selectList(qurey);
        List<RoleVO> roleVOList = BeanUtil.copyList(roleList, RoleVO.class);
        for (RoleVO roleVO : roleVOList) {
            List<PermissionVO> permissionList =  permissionMapper.selectPermissionByRoleId(roleVO.getId());
            roleVO.setPermissionVOS(permissionList);
            List<MenuVO>  menuVOList = roleMenuMapper.selectMenuByRoleId(roleVO.getId());
            roleVO.setMenuVOS(menuVOList);
        }
        return roleVOList;
    }
}
