package com.ebs.platform.business.domain.oldEntity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 单位表
 */
@Data
@Entity
public class Dwb {
/*
DM  代码
DMH 代码号？
CC  层次
ZHON    名称
ZHON_JC 简称
BZ  备注
XH  序号
PYSY    拼音缩写
FL  分类
SBDM    上报代码
SBZHON  上报名称
DWFlag
CCsort  层次sort
 */
    @Id
    private String DM;

    private String DMH;

    private String CC;

    private String ZHON;

    private String ZHON_JC;

    private String BZ;

    private String XH;

    private String PYSY;

    private String FL;

    private String SBDM;

    private String SBZHON;

    private String DWFlag;

    private String CCsort;

}
