package com.yeaile.web.module.user;


import com.yeaile.common.domain.user.dto.RoleDTO;
import com.yeaile.common.domain.user.dto.UserDTO;
import com.yeaile.common.domain.user.vo.RoleVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.user.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */

@RestController
@RequestMapping(name = "角色管理",value = "/api/ceviri-kizlar/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;


    @RequestMapping(name = "新增角色",value = "/v1/role" ,method = RequestMethod.POST)
    public Result addOrUpdate(@RequestBody RoleDTO roleDTO) {
        List<RoleVO> roleVOList= roleService.addOrUpdate(roleDTO);
        return new Result(true, StatusCode.OK, "增加成功",roleVOList);
    }

    @RequestMapping(name = "角色分页",value = {"/v1/role/{id}","/v1/role"} ,method = RequestMethod.GET)
    public Result getRoleList(@PathVariable(value="id",required=false) String id) {
        List<RoleVO> roleVOList = roleService.getRoleList(id);
        return new Result(true, StatusCode.OK, "增加成功",roleVOList);
    }


}

