package com.sd.sdhr.pojo.sd.of;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-11-23 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdof01  implements Serializable {

	private static final long serialVersionUID =  7501861852429877460L;

	/**
	 * OFFER编号
	 */
	private String offerNo;

	/**
	 * 姓名
	 */
	private String memberName;

	/**
	 * 部门
	 */
	private String deptName;

	/**
	 * 岗位
	 */
	private String jobs;

	/**
	 * 入职时间
	 */
	private String empDate;

	/**
	 * 是否接受录用
	 */
	private String isAgree;

	/**
	 * 应届/往届
	 */
	private String isFreGra;

	/**
	 * 电脑
	 */
	private String comStatus;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 入职地址
	 */
	private String jobAddress;

	/**
	 * 薪资
	 */
	private BigDecimal salary;

	/**
	 * 试用期薪资
	 */
	private BigDecimal isDz;

	/**
	 * 状态
	 */
	private String offerStatus;

	/**
	 * 工作流ID
	 */
	private String wfId;

	/**
	 * 提交时间
	 */
	private String submitTime;

	/**
	 * 提交人工号
	 */
	private String submitor;

	/**
	 * 提交人姓名
	 */
	private String submitName;

	/**
	 * 审批状态
	 */
	private String approveStatus;

	/**
	 * 审批人工号
	 */
	private String approver;

	/**
	 * 审批人姓名
	 */
	private String approveName;

	/**
	 * 审批时间
	 */
	private String approveTime;

	/**
	 * 审批备注
	 */
	private String approveRemark;

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
