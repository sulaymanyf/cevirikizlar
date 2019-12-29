package com.yeaile.web.module.tag;



import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.common.domain.ceviri.dto.CeviriDTO;
import com.yeaile.common.domain.ceviri.dto.CeviriQueryDTO;
import com.yeaile.common.domain.ceviri.vo.CeviriVO;
import com.yeaile.common.domain.tag.dto.TagDTO;
import com.yeaile.common.domain.tag.vo.TagVo;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.tag.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-27
 */

@RestController
@RequestMapping("/api/ceviri-kizlar/")
@Api(tags = "标签管理")
public class TagController {

    @Autowired
    private ITagService tagService;


    @ApiOperation("获取单个tag")
    @GetMapping(value = "v1/tag/{id}")
    public Result tag(@PathVariable String id){
        TagVo tagVo =  tagService.tag(id);
        return new Result(true, StatusCode.OK,tagVo);
    }


    @ApiOperation("分页获取tag")
    @GetMapping(value = "v1/tag/{page}/{size}")
    public Result listTag(@PathVariable  int page, @PathVariable int size){
        IPage<TagVo> tagVoIPage =  tagService.listTag();
        return new Result(true, StatusCode.OK,tagVoIPage);
    }



    @ApiOperation("新增")
    @PostMapping(value = "v1/tag")
    public Result addTag(@RequestBody TagDTO tagDTO){
        tagService.tag(tagDTO);
        return new Result(true, StatusCode.OK,"sussess");
    }

    @ApiOperation("新增")
    @PutMapping(value = "v1/tag")
    public Result UpdateTag(@RequestBody TagDTO tagDTO){
        tagService.UpdateTag(tagDTO);
        return new Result(true, StatusCode.OK,"sussess");
    }


    @ApiOperation("新增")
    @DeleteMapping(value = "v1/tag/{id}")
    public Result deleteTag(@PathVariable String id){
        tagService.deleteTag(id);
        return new Result(true, StatusCode.OK,"sussess");
    }
}
