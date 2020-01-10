package com.yeaile.web.module.ceviri;


import com.yeaile.ceviri.service.IMetinTypeService;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeNodeVO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.tree.TreeNode;
import java.util.List;

/**
 * <p>
 * 回答 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */

@RestController
@RequestMapping("/api/ceviri-kizlar/metinType")
@Api(tags = "文章类型管理")
public class MetinTypeController {

    @Autowired
    private IMetinTypeService iMetinTypeService;

    @ApiOperation("修改或添加文章类型")
    @PutMapping(value = "v1/metinType")
    public Result addOrUpdateMetinType(@RequestBody MetinTypeDTO metinTypeDTO){
        iMetinTypeService.addOrUpdateMetinType(metinTypeDTO);
        return new Result(true, StatusCode.OK,"basrildi");
    }

    @ApiOperation("获取单个文章类型")
    @GetMapping(value = "v1/metinType/{id}")
    public Result MetinType(@PathVariable String id){
        MetinTypeVO metinTypeVO =  iMetinTypeService.MetinType(id);
        return new Result(true, StatusCode.OK,metinTypeVO);
    }


    @ApiOperation("获取文章类型树形结构")
    @GetMapping(value = "v1/metinTypeTree")
    public Result MetinTypeTree(){
        List<MetinTypeNodeVO> metinTypeVO =  iMetinTypeService.MetinTypeTree();
        return new Result(true, StatusCode.OK,metinTypeVO);
    }


}

