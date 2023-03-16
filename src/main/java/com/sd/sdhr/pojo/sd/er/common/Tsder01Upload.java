package com.sd.sdhr.pojo.sd.er.common;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sd.sdhr.constant.*;
import com.sd.sdhr.constant.DeptColdConverter;
import com.sd.sdhr.constant.JobsColdConverter;
import lombok.Data;

/**
 * @Title: Tsder01Upload
 * @Author dems
 * @Package com.sd.sdhr.pojo.sd.er.common
 * @Date 2023/3/16 13:34
 * @description: ${description}
 */
@Data
public class Tsder01Upload {

    /**
     * 合同编号
     */
    @ExcelProperty(value="合同编号")
    private String contractNo;

    /**
     * 员工姓名
     */
    @ExcelProperty(value="员工姓名")
    private String memberName;

    /**
     * 性别
     */
    @ExcelProperty(value="性别",converter = ChannelColdConverter.class)
    private String sex;

    /**
     * 面试部门
     */
    @ExcelProperty(value="面试部门",converter = DeptColdConverter.class)
    private String deptName;

    /**
     * 岗位
     */
    @ExcelProperty(value="面试岗位",converter = JobsColdConverter.class)
    private String jobs;

    /**
     * 员工状态
     */
    @ExcelProperty(value="员工状态",converter = IsformalColdConverter.class)
    private String isFormal;

    /**
     * 社保所在地
     */
    @ExcelProperty(value="社保所在地")
    private String secLocation;

    /**
     * 社保信息
     */
    @ExcelProperty(value="社保信息")
    private String secInf;

    /**
     * 民族
     */
    @ExcelProperty(value="民族")
    private String national;

    /**
     * 政治面貌
     */
    @ExcelProperty(value="政治面貌",converter = PoliticsColdConverter.class)
    private String politStatus;

    /**
     * 籍贯
     */
    @ExcelProperty(value="籍贯")
    private String natPlace;

    /**
     * 身份证号
     */
    @ExcelProperty(value="身份证号")
    private String idCard;

    /**
     * 出生年月
     */
    @ExcelProperty(value="出生年月")
    private String birthDate;

    /**
     * 年龄
     */
    @ExcelProperty(value="年龄")
    private String age;

    /**
     * 华夏工资卡号
     */
    @ExcelProperty(value="华夏工资卡")
    private String hxCardId;

    /**
     * 工商银行卡号
     */
    @ExcelProperty(value="工商银行卡")
    private String gsCardId;

    /**
     * 血型
     */
    @ExcelProperty(value="血型",converter = PoliticsColdConverter.class)
    private String bloodType;

    /**
     * 婚姻状况
     */
    @ExcelProperty(value="婚姻状况",converter = PoliticsColdConverter.class)
    private String marryStatus;

    /**
     * 最高学历
     */
    @ExcelProperty(value="最高学历",converter = ExplevelColdConverter.class)
    private String higEdu;

    /**
     * 毕业学校
     */
    @ExcelProperty(value="毕业学校")
    private String universityName;

    /**
     * 所学专业
     */
    @ExcelProperty(value="所学专业")
    private String profession;

    /**
     * 毕业时间
     */
    @ExcelProperty(value="毕业时间")
    private String graDate;

    /**
     * 最高学位
     */
    @ExcelProperty(value="最高学位",converter = IsformalColdConverter.class)
    private String higDegree;

    /**
     * 邮箱
     */
    @ExcelProperty(value="电子邮箱")
    private String email;

    /**
     * 电话
     */
    @ExcelProperty(value="手机号")
    private String tel;

    /**
     * 紧急联系人
     */
    @ExcelProperty(value="紧急联系人")
    private String emeContact;

    /**
     * 与紧急联系人关系
     */
    @ExcelProperty(value="紧急联系人关系")
    private String emeRel;

    /**
     * 紧急联系人电话
     */
    @ExcelProperty(value="紧急联系人电话")
    private String emeTel;

    /**
     * 户籍地址
     */
    @ExcelProperty(value="户籍地址")
    private String thrAddress;

    /**
     * 居住地址
     */
    @ExcelProperty(value="居住地址")
    private String resAddress;

    /**
     * 入司时间
     */
    @ExcelProperty(value="入司时间")
    private String empDate;

    /**
     * 司龄
     */
    @ExcelProperty(value="司龄")
    private String indYear;

    /**
     * 合同签订时间
     */
    @ExcelProperty(value="合同签订时间")
    private String signDate;

    /**
     * 合同到期时间
     */
    @ExcelProperty(value="合同结束时间")
    private String endDate;

    /**
     * 最新结束时间
     */
    @ExcelProperty(value="最新结束时间")
    private String endDateNew;

    /**
     * 合同到期提醒
     */
    @ExcelProperty(value="合同到期提醒")
    private String isRemindC;

    /**
     * 试用期开始时间
     */
    @ExcelProperty(value="试用期开始时间")
    private String probStartDate;

    /**
     * 试用期结束时间
     */
    @ExcelProperty(value="试用期结束时间")
    private String probEndDate;

    /**
     * 试用期到期提醒
     */
    @ExcelProperty(value="试用期结束提醒")
    private String isRemindP;

    /**
     * 备注
     */
    @ExcelProperty(value="备注")
    private String remark;


}
