package com.yeaile.web.module.ceviri;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.ceviri.service.ICeviriService;
import com.yeaile.common.domain.ceviri.dto.CeviriDTO;
import com.yeaile.common.domain.ceviri.dto.CeviriQueryDTO;
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

    @ApiOperation("开始翻译译文")
    @GetMapping(value = "v1/ceviri/{id}")
    public Result ceviri(@PathVariable String id){
        CeviriVO ceviriVO =  iCeviriService.cevir(id);
        return new Result(true, StatusCode.OK,ceviriVO);
    }


    @ApiOperation("分页获取译文")
    @PostMapping(value = "v1/ceviri")
    public Result listCeviri(@RequestBody CeviriQueryDTO ceviriQueryDTO){
        IPage<CeviriVO>  ceviriVOIPage =  iCeviriService.listCeviri(ceviriQueryDTO);
        return new Result(true, StatusCode.OK,ceviriVOIPage);
    }



    @ApiOperation("译文操作")
    @GetMapping(value = "v1/ceviri/{id}/{active}")
    public Result saveCeviri(@PathVariable String id, @PathVariable String active){
        return new Result(true, StatusCode.OK,"sussess");
    }


    @ApiOperation("译文保存")
    @PutMapping(value = "v1/ceviri")
    public Result addCeviri(@RequestBody CeviriDTO ceviriDTO) throws Exception {
        iCeviriService.saveCeviri(ceviriDTO);
        return new Result(true, StatusCode.OK,"Kaydedildi.");
    }

}

