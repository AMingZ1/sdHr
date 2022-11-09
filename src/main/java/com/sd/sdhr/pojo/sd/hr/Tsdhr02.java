package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-09-28 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdhr02  implements Serializable {

	private static final long serialVersionUID =  762259840822684904L;

	/**
	 * 电联记录号
	 */
	@TableId
	private String planNo;

	/**
	 * 岗位需求编号
	 */
	private String reqNo;

	/**
	 * 面试人员姓名
	 */
	private String memberName;

	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 联系状态
	 */
	private String contactStatus;

	/**
	 * 联系人
	 */
	private String contactMember;

	/**
	 * 联系时间
	 */
	private String contactDate;

	/**
	 * 面试时间
	 */
	private String itvDate;

	/**
	 * 面试部门
	 */
	private String deptName;

	/**
	 * 面试岗位
	 */
	private String itvJob;

	/**
	 * 经验
	 */
	private String expLevel;

	/**
	 * 职业状态
	 */
	private String workStatus;

	/**
	 * 预计到岗时间
	 */
	private String arrivalDate;

	/**
	 * 预计薪资
	 */
	private String hopeSalary;

	/**
	 * 面试情况
	 */
	private String itvStatus;

	/**
	 * 初面记录
	 */
	private String itvRemark;

	/**
	 * 面试官
	 */
	private String itver;

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
	private String recCreateName;

	/**
	 * 修改时间
	 */
	private String recModifyTime;

	/**
	 * 修改人
	 */
	private String recModifier;

	/**
	 * 修改人姓名
	 */
	private String recModifyName;

	/**
	 * 删除标记
	 */
	private String deleteFlag;

	/**
	 * 删除时间
	 */
	private String deleteTime;

	/**
	 * 删除人
	 */
	private String deleter;

	/**
	 * 删除人姓名
	 */
	private String deleteName;

}
