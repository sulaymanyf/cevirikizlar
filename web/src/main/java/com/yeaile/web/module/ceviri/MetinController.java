package com.yeaile.web.module.ceviri;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.ceviri.service.IMetinTypeService;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeNodeVO;
import com.yeaile.common.domain.ceviri.vo.MetinTypeVO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.domain.tag.vo.TagVo;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.common.utils.StringUtil;
import com.yeaile.file.entity.MyFile;
import com.yeaile.file.mapper.FileMapper;
import com.yeaile.file.service.IFileService;
import com.yeaile.tag.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 原文 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */

@RestController
@RequestMapping(name = "原文管理",value = "/api/ceviri-kizlar/metin")
@Api(tags = "原文管理")
public class MetinController {

    @Autowired
    private IMetinService iMetinService;

    @Autowired
    private IMetinTypeService iMetinTypeService;

    @Autowired
    private ITagService iTagService;


    @ApiOperation("获取单个译文")
    @GetMapping(name = "获取单个译文",value = "v1/metin/{id}")
    public Result metin(@PathVariable String id){
        MetinVO metinVO =  iMetinService.metin(id);
        return new Result(true, StatusCode.OK,metinVO);
    }
//Request URL: http://localhost:8000/api/ceviri-kizlar/v1/metin?current=1&pageSize=10
    //Request URL: http://localhost:8000/api/ceviri-kizlar/v1/metin?current=1&pageSize=10&name=ds%20

    @ApiOperation("修改")
    @PutMapping(name = "修改",value = "v1/metin")
    public Result updateMetin(@RequestBody MetinDTO metinDTO){
        iMetinService.addOrUpdateMetin(metinDTO);
        return new Result(true, StatusCode.OK,"basrildi");
    }
    //{current}/{pageSize}

    @ApiOperation("分页查看原文")
    @GetMapping(name = "分页查看原文",value = "v1/metin")
    public Result listMetin(@RequestParam(defaultValue ="1" ,name= "current", required = false) int current,@RequestParam(defaultValue ="10" ,name="pageSize", required = false) int pageSize, @RequestParam( required = false) String queryDtoStr){
        MetinQueryDTO metinDTO = new MetinQueryDTO();
        if (StringUtil.isNotBlank(queryDtoStr)){
             metinDTO = JSON.parseObject(queryDtoStr, MetinQueryDTO.class);
        }
        System.out.println(JSON.toJSONString(metinDTO));
        IPage<MetinVO> metinVOIPage =  iMetinService.listMetin(current, pageSize,metinDTO);
        return new Result(true, StatusCode.OK,metinVOIPage);
    }


    @ApiOperation("添加原文")
    @PostMapping(name = "添加原文",value = "v1/metin")
    public Result addMetin(@RequestBody MetinDTO metinDTO) throws IOException {
        iMetinService.addMetin(metinDTO);
        return new Result(true, StatusCode.OK,"basrildi");
    }


    @ApiOperation("修改或添加文章类型")
    @PutMapping(name = "修改或添加文章类型",value = "v1/metin/type")
    public Result addOrUpdateMetinType(@RequestBody MetinTypeDTO metinTypeDTO){
        List<MetinTypeVO> typeVOList= iMetinTypeService.addOrUpdateMetinType(metinTypeDTO);
        return new Result(true, StatusCode.OK,"basrildi",typeVOList);
    }

    @ApiOperation("获取单个文章类型")
    @GetMapping(name = "获取单个文章类型",value = "v1/metin/type/{id}")
    public Result MetinType(@PathVariable String id){
        MetinTypeVO metinTypeVO =  iMetinTypeService.MetinType(id);
        return new Result(true, StatusCode.OK,metinTypeVO);
    }


    @ApiOperation("获取文章类型树形结构")
    @GetMapping(name = "获取文章类型树形结构",value = "v1/metin/Tree")
    public Result MetinTypeTree(){
        List<MetinTypeNodeVO> metinTypeVO =  iMetinTypeService.MetinTypeTree();
        return new Result(true, StatusCode.OK,metinTypeVO);
    }

    @ApiOperation("文章类型列表")
    @GetMapping(name = "文章类型列表",value = "v1/metin/tipList")
    public Result tipList(){
        List<MetinTypeVO> metinTypeVO =  iMetinTypeService.tipList();
        return new Result(true, StatusCode.OK,metinTypeVO);
    }

    @ApiOperation("删除类型")
    @DeleteMapping(name = "删除类型",value = "v1/metin/type/{id}")
    public Result deleteTip(@PathVariable String id){
        List<MetinTypeVO> metinTypeVO =  iMetinTypeService.delete(id);
        return new Result(true, StatusCode.OK,metinTypeVO);
    }


}

