package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Tsdhr01Export {

    /**
     * 需求编号
     */
    @ExcelProperty(value="岗位需求编号",index = 0)
    @ColumnWidth(15)
    private String reqNo;

    /**
     * 年份
     */
    @ExcelProperty(value="年份",index = 1)
    @ColumnWidth(6)
    private String year;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "需求部门",index = 2)
    @ColumnWidth(15)
    private String deptName;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "岗位名称",index = 3)
    @ColumnWidth(15)
    private String itvJob;


    /**
     * 需求数量
     */
    @ExcelProperty(value ="需求数量",index = 4)
    @ColumnWidth(15)
    private BigDecimal requireNum;

    /**
     * 实际完成数量
     */
    @ExcelProperty(value = "需求编号",index = 5)
    @ColumnWidth(15)
    private BigDecimal realNum;

    /**
     * 岗位要求
     */
    @ExcelProperty(value = "岗位要求",index = 6)
    private String jobRequire;

    /**
     * 需求联系人
     */
    @ExcelProperty(value = "需求联系人",index = 7)
    private String requireContact;

    /**
     * 责任人
     */
    @ExcelProperty(value = "责任人",index = 8)
    private String dutyPerson;

    /**
     * 预计完成实际
     */
    @ExcelProperty(value = "预计完成实际",index = 9)
    private String planEndDate;

    /**
     * 面试方式
     */
    @ExcelProperty(value = "面试方式",index = 10)
    private String itvWays;

    /**
     * 是否紧急
     */
    @ExcelProperty(value = "是否紧急",index = 11)
    private String isEme;


}
