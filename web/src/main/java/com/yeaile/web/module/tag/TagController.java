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
@RequestMapping(name = "标签管理", value = "/api/ceviri-kizlar/tag")
@Api(tags = "标签管理")
public class TagController {

    @Autowired
    private ITagService tagService;


    @ApiOperation("获取单个tag")
    @GetMapping(name = "获取单个tag",value = "v1/tag/{id}")
    public Result tag(@PathVariable String id){
        TagVo tagVo =  tagService.tag(id);
        return new Result(true, StatusCode.OK,tagVo);
    }


    @ApiOperation("分页获取tag")
    @GetMapping(name = "分页获取tag",value ={ "v1/tag/{size}/{page}"})
    public Result listTag(@PathVariable(required = false, name ="page" ) Integer page, @PathVariable(required = false, name ="size" ) Integer size){
        if (page==null) page=0;
        if (size==null) size=10;
        IPage<TagVo> tagVoIPage =  tagService.listTag(page,size);
        return new Result(true, StatusCode.OK,tagVoIPage);
    }



    @ApiOperation("新增修改")
    @PostMapping(name = "新增",value = "v1/tag")
    public Result addTag(@RequestBody TagDTO tagDTO){
        IPage<TagVo> page = tagService.tag(tagDTO);
        return new Result(true, StatusCode.OK,"sussess",page);
    }


    @ApiOperation("删除")
    @DeleteMapping(name = "删除",value = "v1/tag/{id}")
    public Result deleteTag(@PathVariable String id){
        IPage<TagVo> iPage = tagService.deleteTag(id);
        return new Result(true, StatusCode.OK,"sussess",iPage);
    }
}
