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
@RequestMapping(name = "文章类型管理",value = "/api/ceviri-kizlar/metinType")
@Api(tags = "文章类型管理")
public class MetinTypeController {




}

