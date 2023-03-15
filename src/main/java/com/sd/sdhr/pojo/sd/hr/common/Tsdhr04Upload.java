package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sd.sdhr.constant.ContastatusColdConverter;
import com.sd.sdhr.constant.DeptColdConverter;
import com.sd.sdhr.constant.ExplevelColdConverter;
import com.sd.sdhr.constant.OtherColdConverter;

import java.math.BigDecimal;

/**
 * @Title: Tsdhr04Upload
 * @Author dems
 * @Package com.sd.sdhr.pojo.sd.hr.common
 * @Date 2023/3/14 18:11
 * @description: ${description}
 */
public class Tsdhr04Upload {

    /**
     * 电联记录号
     */
    @ExcelProperty(value="电联记录号")
    private String planNo;

    /**
     * 面试时间
     */
    @ExcelProperty(value="面试时间")
    private String itvDate;

    /**
     * 面试官
     */
    @ExcelProperty(value="面试官")
    private String itver;

    /**
     * 面试方式
     */
    @ExcelProperty(value="面试方式",converter = OtherColdConverter.class)
    private String itvWays;

    /**
     * 面试人员
     */
    @ExcelProperty(value="面试人员")
    private String memberName;

    /**
     * 学校
     */
    @ExcelProperty(value="学校")
    private String universityName;

    /**
     * 学历
     */
    @ExcelProperty(value="学历",converter = ExplevelColdConverter.class)
    private String educationBckr;

    /**
     * 专业
     */
    @ExcelProperty(value="专业")
    private String profession;


    /**
     * 成绩1
     */
    @ExcelProperty(value="沟通、表达能力(20)")
    @TableField(value="score_1")
    private int score1;

    /**
     * 成绩1
     */
    @ExcelProperty(value="判断、分析、反应能力(10)")
    @TableField(value="score_2")
    private int score2;

    /**
     * 成绩1
     */
    @ExcelProperty(value="责任心、纪律性(10)")
    @TableField(value="score_3")
    private int score3;

    /**
     * 成绩1
     */
    @ExcelProperty(value="专业知识(30)")
    @TableField(value="score_4")
    private int score4;

    /**
     * 成绩1
     */
    @ExcelProperty(value="项目经验(15)")
    @TableField(value="score_5")
    private int score5;

    /**
     * 成绩1
     */
    @ExcelProperty(value="学习能力(15)")
    @TableField(value="score_6")
    private int score6;

    /**
     * 总分
     */
    @ExcelProperty(value="总分")
    private int sumScore;

    /**
     * 面试结果
     */
    @ExcelProperty(value="面试结果",converter = ContastatusColdConverter.class)
    private String itvStatus;


    /**
     * 报道时间
     */
    @ExcelProperty(value="人员编号")
    private String arrivalDate;


    /**
     * 入库主观评价
     */
    @ExcelProperty(value="主观评价")
    private String evaluation;



}
