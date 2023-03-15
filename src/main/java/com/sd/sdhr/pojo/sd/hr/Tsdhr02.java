package com.sd.sdhr.pojo.sd.hr;

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
public class Tsdhr02  implements Serializable {

	private static final long serialVersionUID =  6892246122990758792L;

	/**
	 * 电联记录号
	 */
	@ExcelProperty(value="电联记录号",index = 0)
	private String planNo;

	/**
	 * 岗位需求编号
	 */
	@ExcelProperty(value="岗位需求编号",index = 1)
	private String reqNo;

	/**
	 * 面试人员姓名
	 */
	@ExcelProperty(value="面试人员姓名",index = 2)
	private String memberName;

	/**
	 * 联系电话
	 */
	@ExcelProperty(value="联系电话",index = 3)
	private String tel;

	/**
	 * 邮箱
	 */
	@ExcelProperty(value="邮箱",index = 4)
	private String email;

	/**
	 * 联系状态
	 */
	@ExcelProperty(value="联系状态",index = 5,converter = ContastatusColdConverter.class)
	private String contactStatus;

	/**
	 * 联系人
	 */
	@ExcelProperty(value="联系人",index = 6)
	private String contactMember;

	/**
	 * 联系时间
	 */
	@ExcelProperty(value="联系时间",index = 7)
	private String contactDate;

	/**
	 * 面试时间
	 */
	@ExcelProperty(value="面试时间",index = 8)
	private String itvDate;

	/**
	 * 面试部门
	 */
	@ExcelProperty(value="面试部门",index = 9,converter = DeptColdConverter.class)
	private String deptName;

	/**
	 * 面试岗位
	 */
	@ExcelProperty(value="面试岗位",index = 10,converter = JobsColdConverter.class)
	private String itvJob;

	/**
	 * 经验
	 */
	@ExcelProperty(value="经验",index = 11,converter = ExplevelColdConverter.class)
	private String expLevel;

	/**
	 * 职业状态
	 */
	@ExcelProperty(value="职业状态",index = 12,converter = OtherColdConverter.class)
	private String workStatus;

	/**
	 * 预计到岗时间
	 */
	@ExcelProperty(value="预计到岗时间",index = 13)
	private String arrivalDate;

	/**
	 * 预计薪资
	 */
	@ExcelProperty(value="预计薪资",index = 14)
	private String hopeSalary;

	/**
	 * 面试情况
	 */
	@ExcelProperty(value="面试情况",index = 15,converter = ContastatusColdConverter.class)
	private String itvStatus;

	/**
	 * 初面记录
	 */
	@ExcelProperty(value="初面记录",index = 16)
	private String itvRemark;

	/**
	 * 面试官
	 */
	@ExcelProperty(value="面试官",index = 17)
	private String itver;

	/**
	 * 备注
	 */
	@ExcelProperty(value="备注",index = 18)
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
