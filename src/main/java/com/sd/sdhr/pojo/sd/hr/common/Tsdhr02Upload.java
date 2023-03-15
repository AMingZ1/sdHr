package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sd.sdhr.constant.ContastatusColdConverter;
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
public class Tsdhr02Upload {

    /**
     * 年份
     */
    @ExcelProperty(value = "岗位需求编号")
    private String reqNo;

    /**
     * 人员姓名
     */
    @ExcelProperty(value = "人员姓名")
    private String memberName;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String tel;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private BigDecimal email;

    /**
     * 联系状态
     */
    @ExcelProperty(value = "联系状态",converter = ContastatusColdConverter.class)
    private String contactStatus;

    /**
     * 需求联系人
     */
    @ExcelProperty(value = "联系人")
    private String contactMember;

    /**
     * 联系时间
     */
    @ExcelProperty(value = "联系时间")
    private String contactDate;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

}
