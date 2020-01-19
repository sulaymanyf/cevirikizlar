package com.yeaile.user.service.impl;


import com.yeaile.common.constant.PermissionStatus;
import com.yeaile.common.domain.user.vo.PermissionAllVO;
import com.yeaile.common.domain.user.vo.PermissionNodeVO;
import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.user.entity.Permission;
import com.yeaile.user.mapper.PermissionMapper;
import com.yeaile.user.service.IPermissionService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Service
public class PermissionServiceImpl implements IPermissionService {


    @Resource
    private PermissionMapper permissionMapper;

    @Override
    @Cacheable
    public List<PermissionVO> getAllPermissionWithRole() {
        permissionMapper.getAllPermissionWithRole();
        return null;
    }

    @Override
    public List<String> getAllPathsByUserName(String username) {
        List<String> permissionVOS = permissionMapper.getAllPathsByUserName(username);

        return permissionVOS;
    }

    @Override
    public List<Permission> getAllPermission() {
        List<Permission> permissionList = permissionMapper.findAll();
        return permissionList;
    }

    @Override
    public Permission getPermissionByUrl(String url) {
        Permission permission = permissionMapper.findPermissionByUrl(url);
        return permission;
    }

    @Override
    public void addPermission(List<Permission> oldPermissionList) {
        List<Permission> noPid = new ArrayList<>();
        List<Permission> insert = new ArrayList<>();
        for (Permission permission : oldPermissionList) {
            if (permission.getMethod() != null && !permission.getUrl().contains("api") && permission.getPid() == null) {
                permission.setName(permission.getName().split("#")[1]);
                permission.setId(IdWorkerUtil.getIdStr());
                permission.setType(PermissionStatus.BUTTON.getCode());
                noPid.add(permission);
            } else if (permission.getPid() != null) {
                permission.setName(permission.getName().split("#")[1]);
                permission.setId(IdWorkerUtil.getIdStr());
                permission.setType(PermissionStatus.BUTTON.getCode());
                insert.add(permission);
            } else if (permission.getUrl().contains("api")) {
                permission.setId(IdWorkerUtil.getIdStr());
                permission.setType(PermissionStatus.MENU.getCode());
                permission.setName(permission.getName().split("#")[0]);
                insert.add(permission);
            }

        }
        System.out.println(oldPermissionList);
        for (Permission perm : insert) {
            permissionMapper.insert(perm);
        }
        for (Permission nopid : noPid) {
            String[] split = nopid.getUrl().split("/");
            String url = split[split.length - 1];
            String pid = permissionMapper.getPidByUrl(url);
            nopid.setPid(pid);
            permissionMapper.insert(nopid);
        }

    }

    @Override
    public List<Permission> getAllPathsByUserNameAndMethod(String url) {
        List<Permission> allPathsByUserNameAndMethod = permissionMapper.getAllPathsByUserNameAndMethod(url);
        return allPathsByUserNameAndMethod;
    }

    @Override
    public List<PermissionVO> mainMenu() {
        List<Permission> permissionList = permissionMapper.getMainMenu();
        return BeanUtil.copyList(permissionList, PermissionVO.class);
    }

    @Override
    public List<PermissionAllVO> getAPermission() {
        List<PermissionAllVO> pids = permissionMapper.selectListAll();
        for (PermissionAllVO pid : pids) {
            List<String> sonPermis = permissionMapper.getSonPermis(pid.getId());
            pid.setPerName(sonPermis);
        }
        return pids;
    }

    @Override
    public List<PermissionVO> getPermission(String id) {
        List<PermissionVO> permissionVOS = permissionMapper.selectByPid(id);
        return permissionVOS;
    }

    @Override
    public List<PermissionNodeVO> getPermissionTree() {
        List<Permission> permissionList = permissionMapper.selectList(null);
        ArrayList<PermissionNodeVO> permissionNodeVOS = new ArrayList<>();
        for (Permission permiss : permissionList) {
            if (permiss != null) {
                permissionNodeVOS.add(new PermissionNodeVO(permiss.getId(), permiss.getPid(), permiss.getName()));
            }
        }
        List<PermissionNodeVO> menuTree = ListToTree(permissionNodeVOS);
        return menuTree;
    }


    private static List<PermissionNodeVO> ListToTree(ArrayList<PermissionNodeVO> list) {

        Map<String, PermissionNodeVO> mapTmp = new HashMap<>();
        for (PermissionNodeVO current : list) {
            mapTmp.put(current.getId(), current);
        }
        System.out.println(mapTmp);
        List<PermissionNodeVO> finalList = new ArrayList<>();
        mapTmp.forEach((k, v) -> {
            if ("".equals(v.getPid()) || "0".equals(v.getPid())) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getPid()).getChildren().add(v);
            }
        });
        return finalList;
    }
}
