package com.yeaile.common.utils.conn;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefan
 * @Classname TreeNode
 * @Description TODO
 * @Date 2019/12/28 4:03 下午
 * @Created by SulaymanYf
 */
@Data
public class BaseNode {

    protected String id;
    protected String pid;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List<BaseNode> children = new ArrayList<>();



}
