package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.sd.sdhr.constant.DeptColdConverter;
import com.sd.sdhr.constant.JobsColdConverter;
import com.sd.sdhr.constant.OtherColdConverter;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Title: Tsdhr01Upload
 * @Author dems
 * @Package com.sd.sdhr.pojo.sd.hr.common
 * @Date 2023/3/6 13:49
 * @description: ${description}
 */
@Data
public class Tsdhr01Upload {
    @ExcelIgnore
    private String reqNo;

    /**
     * 年份
     */
    @ExcelProperty(value = "年份")
    private String year;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门名称",converter = DeptColdConverter.class)//
    private String deptName;

    /**
     * 岗位名称
     */
    @ExcelProperty(value = "岗位名称",converter = JobsColdConverter.class)
    private String itvJob;

    /**
     * 需求数量
     */
    @ExcelProperty
    private BigDecimal requireNum;

    /**
     * 岗位要求
     */
    @ExcelProperty
    private String jobRequire;

    /**
     * 需求联系人
     */
    @ExcelProperty
    private String requireContact;

    /**
     * 责任人
     */
    @ExcelProperty
    private String dutyPerson;

    /**
     * 预计完成时间
     */
    @ExcelProperty
    private String planEndDate;

    /**
     * 面试方式
     */
    @ExcelProperty(value = "面试方式",converter = OtherColdConverter.class)
    private String itvWays;

    /**
     * 是否紧急
     */
    @ExcelProperty
    private String isEme;

}
