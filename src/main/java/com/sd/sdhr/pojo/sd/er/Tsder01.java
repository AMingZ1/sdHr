package com.sd.sdhr.pojo.sd.er;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.sd.sdhr.constant.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-12-01 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsder01  implements Serializable {

	private static final long serialVersionUID =  1122177988156687542L;

	/**
	 * 员工编码
	 */
	@ExcelProperty(value="员工编码",index = 0)
	private String memberId;

	/**
	 * 员工姓名
	 */
	@ExcelProperty(value="员工姓名",index = 1)
	private String memberName;

	/**
	 * 合同编号
	 */
	@ExcelProperty(value="合同编号",index = 2)
	private String contractNo;

	/**
	 * 部门
	 */
	@ExcelProperty(value="所在部门",index = 3,converter = DeptColdConverter.class)
	private String deptName;

	/**
	 * 二级事业部
	 */
	@ExcelProperty(value = "二级事业部",index = 4,converter = SecondLevelDeptColdConverter.class)
	private String secondLevelDept;

	/**
	 * 岗位
	 */
	@ExcelProperty(value="所在岗位",index = 5,converter = JobsColdConverter.class)
	private String jobs;

	/**
	 * 是否已转正
	 */
	@ExcelProperty(value="员工状态",index = 6,converter = IsformalColdConverter.class)
	private String isFormal;

	/**
	 * 邮箱
	 */
	@ExcelProperty(value="邮箱",index = 7)
	private String email;

	/**
	 * 电话
	 */
	@ExcelProperty(value="电话",index = 8)
	private String tel;

	/**
	 * 性别
	 */
	@ExcelProperty(value="性别",index = 9,converter = ChannelColdConverter.class)
	private String sex;

	/**
	 * 社保所在地
	 */
	@ExcelProperty(value="社保所在地",index = 10)
	private String secLocation;

	/**
	 * 社保信息
	 */
	@ExcelProperty(value="社保信息",index = 11)
	private String secInf;

	/**
	 * 属地化
	 */
	@ExcelProperty(value="属地化",index = 12)
	private String localInf;


	/**
	 * 民族
	 */
	@ExcelProperty(value="民族",index = 13)
	private String national;

	/**
	 * 政治面貌
	 */
	@ExcelProperty(value="政治面貌",index = 14,converter = PoliticsColdConverter.class)
	private String politStatus;


	/**
	 * 籍贯
	 */
	@ExcelProperty(value="籍贯",index = 15)
	private String natPlace;


	/**
	 * 身份证号
	 */
	@ExcelProperty(value="身份证号",index = 16)
	private String idCard;

	/**
	 * 出生年月
	 */
	@ExcelProperty(value="出生年月",index = 17)
	private String birthDate;

	/**
	 * 年龄
	 */
	@ExcelProperty(value="年龄",index = 18)
	private String age;

	/**
	 * 华夏工资卡号
	 */
	@ExcelProperty(value="华夏工资卡号",index = 19)
	private String hxCardId;

	/**
	 * 工商银行卡号
	 */
	@ExcelProperty(value="工商银行卡号",index = 20)
	private String gsCardId;

	/**
	 * 血型
	 */
	@ExcelProperty(value="血型",index = 21,converter = PoliticsColdConverter.class)
	private String bloodType;

	/**
	 * 婚姻状况
	 */
	@ExcelProperty(value="婚姻状况",index = 22,converter = PoliticsColdConverter.class)
	private String marryStatus;

	/**
	 * 最高学历
	 */
	@ExcelProperty(value="最高学历",index = 23,converter = ExplevelColdConverter.class)
	private String higEdu;

	/**
	 * 毕业学校
	 */
	@ExcelProperty(value="毕业学校",index = 24)
	private String universityName;

	/**
	 * 毕业时间
	 */
	@ExcelProperty(value="毕业时间",index = 25)
	private String graDate;

	/**
	 * 所学专业
	 */
	@ExcelProperty(value="所学专业",index = 26)
	private String profession;

	/**
	 * 最高学位
	 */
	@ExcelProperty(value="最高学位",index = 27,converter = IsformalColdConverter.class)
	private String higDegree;

	/**
	 * 紧急联系人
	 */
	@ExcelProperty(value="紧急联系人",index = 28)
	private String emeContact;

	/**
	 * 与紧急联系人关系
	 */
	@ExcelProperty(value="与紧急联系人关系",index = 29)
	private String emeRel;

	/**
	 * 紧急联系人电话
	 */
	@ExcelProperty(value="紧急联系人电话",index = 30)
	private String emeTel;

	/**
	 * 户籍地址
	 */
	@ExcelProperty(value="户籍地址",index = 31)
	private String thrAddress;

	/**
	 * 居住地址
	 */
	@ExcelProperty(value="居住地址",index = 32)
	private String resAddress;

	/**
	 * 入司时间
	 */
	@ExcelProperty(value="入司时间",index = 33)
	private String empDate;

	/**
	 * 司龄
	 */
	@ExcelProperty(value="司龄",index = 34)
	private String indYear;

	/**
	 * 合同签订时间
	 */
	@ExcelProperty(value="合同签订时间",index = 35)
	private String signDate;

	/**
	 * 合同到期时间
	 */
	@ExcelProperty(value="合同结束时间",index = 36)
	private String endDate;

	/**
	 * 最新结束时间
	 */
	@ExcelProperty(value="最新结束时间",index = 37)
	private String endDateNew;

	/**
	 * 合同到期提醒
	 */
	@ExcelProperty(value="合同到期提醒",index = 38)
	private String isRemindC;

	/**
	 * 试用期开始时间
	 */
	@ExcelProperty(value="试用期开始时间",index = 39)
	private String probStartDate;

	/**
	 * 试用期结束时间
	 */
	@ExcelProperty(value="试用期结束时间",index = 40)
	private String probEndDate;

	/**
	 * 试用期到期提醒
	 */
	@ExcelProperty(value="试用期到期提醒",index = 41)
	private String isRemindP;

	/**
	 * 转正时间
	 */
	@ExcelIgnore
	private String formalDare;

	/**
	 * 备注
	 */
	@ExcelIgnore
	private String remark;

	/**
	 * 创建时间
	 */
	@ExcelIgnore
	private String recCreateTime;

	/**
	 * 创建人
	 */
	@ExcelIgnore
	private String recCreator;

	/**
	 * 创建人姓名
	 */
	@ExcelIgnore
	private String recCreateName;

	/**
	 * 修改时间
	 */
	@ExcelIgnore
	private String recModifyTime;

	/**
	 * 修改人
	 */
	@ExcelIgnore
	private String recModifier;

	/**
	 * 修改人姓名
	 */
	@ExcelIgnore
	private String recModifyName;

	/**
	 * 删除标记
	 */
	@ExcelIgnore
	private String deleteFlag;

	/**
	 * 删除时间
	 */
	@ExcelIgnore
	private String deleteTime;

	/**
	 * 删除人
	 */
	@ExcelIgnore
	private String deleter;

	/**
	 * 删除人姓名
	 */
	@ExcelIgnore
	private String deleteName;

}
