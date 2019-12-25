package com.yeaile.web.module.ceviri;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.ceviri.service.IMetinService;
import com.yeaile.common.domain.ceviri.dto.MetinDTO;
import com.yeaile.common.domain.ceviri.dto.MetinQueryDTO;
import com.yeaile.common.domain.ceviri.vo.MetinVO;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 原文 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-25
 */

@RestController
@RequestMapping("/api/ceviri-kizlar/")
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


    @ApiOperation("修改或添加原文")
    @PutMapping(value = "v1/metin")
    public Result addOrUpdateMetin(@RequestBody MetinDTO metinDTO){
        iMetinService.addOrUpdateMetin(metinDTO);
        return new Result(true, StatusCode.OK,"basrildi");
    }

    @ApiOperation("分页查看原文")
    @PostMapping(value = "v1/metin")
    public Result listMetin(@RequestBody MetinQueryDTO metinDTO){
        IPage<MetinVO> metinVOIPage =  iMetinService.listMetin(metinDTO);
        return new Result(true, StatusCode.OK,metinVOIPage);
    }

}

