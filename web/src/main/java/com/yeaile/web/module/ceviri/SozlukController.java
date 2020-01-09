package com.yeaile.web.module.ceviri;

import com.yeaile.ceviri.service.IEngSozlukService;
import com.yeaile.ceviri.service.IMetinTypeService;
import com.yeaile.common.domain.ceviri.dto.MetinTypeDTO;
import com.yeaile.common.domain.ceviri.vo.EngSozVo;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname SozlukController
 * @Description TODO
 * @Date 2020/1/8 8:42 下午
 * @Created by SulaymanYf
 */
@RestController
@RequestMapping("/api/ceviri-kizlar/sozluk")
@Api(tags = "字典查询")
public class SozlukController {

    @Autowired
    private IEngSozlukService iEngSozlukService;

    @ApiOperation("关键字搜素")
    @GetMapping(value = "/v1/en/list/{keyword}")
    public Result getWordList(@PathVariable String keyword){
        List<String> wordList = iEngSozlukService.getWordList(keyword);
        return new Result(true, StatusCode.OK,wordList);
    }


    @ApiOperation("查询单词")
    @GetMapping(value = "/v1/en/{keyword}")
    public Result getWorTranslate(@PathVariable String keyword){
        EngSozVo engSozVo = iEngSozlukService.getWorTranslate(keyword);
        System.out.println(engSozVo);
        return new Result(true, StatusCode.OK,engSozVo);
    }


}
