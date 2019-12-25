package com.yeaile.web.module.ceviri;


import com.yeaile.ceviri.service.ICeviriService;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;
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
@Api(tags = "译文管理")
public class CeviriController {


    @Autowired
    private ICeviriService iCeviriService;

    @ApiOperation("获取单个译文")
    @GetMapping(value = "v1/ceviri")
    public Result cevir(@PathVariable String id){
        CeviriVO ceviriVO =  iCeviriService.cevir(id);
        return new Result(true, StatusCode.OK,ceviriVO);
    }



}

