package com.yeaile.web.module.user;


import com.yeaile.common.domain.user.dto.UserRegDto;
import com.yeaile.common.domain.user.vo.PermissionAllVO;
import com.yeaile.common.domain.user.vo.PermissionNodeVO;
import com.yeaile.common.domain.user.vo.PermissionVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.user.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */

@RestController
@RequestMapping(name = "权限管理",value = "/api/ceviri-kizlar/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;


    @RequestMapping(name = "获取一级菜单",value = "v1/permission/mainmenu",method = RequestMethod.GET)
    public Result mainMenu(){
        List<PermissionVO> permissionVOs = permissionService.mainMenu();
        return new Result(true, StatusCode.OK,permissionVOs);
    }

    @RequestMapping(name = "获取所以权限列表",value = "v1/permission/all",method = RequestMethod.GET)
    public Result getAllPermission(){
        List<PermissionAllVO> permissionVOs = permissionService.getAPermission();
        return new Result(true, StatusCode.OK,permissionVOs);
    }

    @RequestMapping(name = "根据ID获取权限",value = "v1/permission/{id}",method = RequestMethod.GET)
    public Result getPermission(@PathVariable String id){
        List<PermissionVO> permissionVOs = permissionService.getPermission(id);
        return new Result(true, StatusCode.OK,permissionVOs);
    }

    @RequestMapping(name = "权限树",value = "v1/permission/tree",method = RequestMethod.GET)
    public Result getPermissionTree(){
        List<PermissionNodeVO> permissionVOs = permissionService.getPermissionTree();
        return new Result(true, StatusCode.OK,permissionVOs);
    }


}

