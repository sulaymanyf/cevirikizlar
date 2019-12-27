package com.yeaile.web.module.tag;



import com.yeaile.tag.service.ITagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/user/")
public class TagController {

    @Autowired
    private ITagService tagService;
}

