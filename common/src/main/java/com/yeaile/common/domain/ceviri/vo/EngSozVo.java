package com.yeaile.common.domain.ceviri.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

/**
 * eng_sozluk
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngSozVo implements Serializable {
    private Long id;

    private String word;

    private String phonetic;

    private String definition;

    private String translation;

    private String pos;

    private String collins;

    private String oxford;

    private String tag;

    private String bnc;

    private Long frq;

    private String exchange;

    private String detail;

    private String audio;

    private String turkish;

    private static final long serialVersionUID = 1L;
}