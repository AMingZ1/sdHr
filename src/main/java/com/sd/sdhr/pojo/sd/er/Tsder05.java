package com.sd.sdhr.pojo.sd.er;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.sd.sdhr.constant.SecondLevelDeptColdConverter;
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
public class Tsder05  implements Serializable {

	private static final long serialVersionUID =  1992392963210220770L;

	/**
	 * 员工编码
	 */
	private String memberId;

	/**
	 * 员工姓名
	 */
	private String memberName;

	/**
	 * 公司
	 */
	private String company;

	/**
	 * 部门
	 */
	private String deptName;

	/**
	 * 二级事业部
	 */
	private String secondLevelDept;

	/**
	 * 岗位
	 */
	private String jobs;

	/**
	 * 入职日期
	 */
	private String empDate;

	/**
	 * 离职日期
	 */
	private String depDate;

	/**
	 * 离职原因
	 */
	private String depReason;

	/**
	 * 申请日期
	 */
	private String applyDate;

	/**
	 * 本公司审批人
	 */
	private String approver;

	/**
	 * 本公司审批时间
	 */
	private String approveDate;

	/**
	 * 本公司审批意见
	 */
	private String approveRemark;

	/**
	 * 审批状态
	 */
	private String approveStatus;

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
