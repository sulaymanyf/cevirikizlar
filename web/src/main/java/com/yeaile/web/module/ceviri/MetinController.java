package com.yeaile.web.module.ceviri;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.common.utils.StringUtil;
import com.yeaile.file.entity.MyFile;
import com.yeaile.file.mapper.FileMapper;
import com.yeaile.file.service.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * <p>
 * 原文 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */

@RestController
@RequestMapping("/api/ceviri-kizlar/metin")
@Api(tags = "原文管理")
public class MetinController {

    @Autowired
    private IMetinService iMetinService;




    @ApiOperation("获取单个译文")
    @GetMapping(value = "v1/metin/{id}")
    public Result metin(@PathVariable String id){
        MetinVO metinVO =  iMetinService.metin(id);
        return new Result(true, StatusCode.OK,metinVO);
    }
//Request URL: http://localhost:8000/api/ceviri-kizlar/v1/metin?current=1&pageSize=10
    //Request URL: http://localhost:8000/api/ceviri-kizlar/v1/metin?current=1&pageSize=10&name=ds%20

    @ApiOperation("修改")
    @PutMapping(value = "v1/metin")
    public Result updateMetin(@RequestBody MetinDTO metinDTO){
        iMetinService.addOrUpdateMetin(metinDTO);
        return new Result(true, StatusCode.OK,"basrildi");
    }
    //{current}/{pageSize}

    @ApiOperation("分页查看原文")
    @GetMapping(value = "v1/metin")
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
    @PostMapping(value = "v1/metin")
    public Result addMetin(@RequestBody MetinDTO metinDTO) throws IOException {
        iMetinService.addMetin(metinDTO);
        return new Result(true, StatusCode.OK,"basrildi");
    }

}

