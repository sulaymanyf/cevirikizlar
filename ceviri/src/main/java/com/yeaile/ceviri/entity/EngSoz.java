package com.yeaile.ceviri.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * eng_sozluk
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString
@TableName("eng_sozluk")
public class EngSoz implements Serializable {
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