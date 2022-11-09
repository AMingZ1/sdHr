package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.sd.sdhr.constant.GenderConverter;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Tsdhr01Export {

    /**
     * 需求编号
     */
    @ExcelProperty("需求编号")
    @ColumnWidth(15)
    private String reqNo;

    /**
     * 年份
     */
    @ExcelProperty("年份")
    @ColumnWidth(6)
    private String year;

    /**
     * 部门名称
     */
    @ExcelProperty(value = "部门名称", converter = GenderConverter.class)
    @ColumnWidth(15)
    private String deptName;

    /**
     * 部门名称
     */
    private String itvJob;

    /**
     * 需求数量
     */
    private BigDecimal requireNum;

    /**
     * 实际完成数量
     */
    private BigDecimal realNum;

    /**
     * 岗位要求
     */
    private String jobRequire;

    /**
     * 需求联系人
     */
    private String requireContact;

    /**
     * 责任人
     */
    private String dutyPerson;

    /**
     * 预计完成实际
     */
    private String planEndDate;

    /**
     * 面试方式
     */
    private String itvWays;

    /**
     * 是否紧急
     */
    private String isEme;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private String recCreateTime;

    /**
     * 创建人
     */
    private String recCreator;

    /**
     * 创建人姓名
     */
    private String recCreatorName;
}
