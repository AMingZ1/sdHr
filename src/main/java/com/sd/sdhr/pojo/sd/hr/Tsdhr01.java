package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-10-30 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdhr01  implements Serializable {

	private static final long serialVersionUID =  7458678835542504626L;

	/**
	 * 需求编号
	 */
	private String reqNo;

	/**
	 * 年份
	 */
	private String year;

	/**
	 * 部门名称
	 */
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

	/**
	 * 修改时间
	 */
	private String recModifyTime;

	/**
	 * 修改人
	 */
	private String recModifier;

	/**
	 * 修改时间
	 */
	private String recModifyName;

	/**
	 * 修改时间
	 */
	private String deleteFlag;

	/**
	 * 修改时间
	 */
	private String deleteTime;

	/**
	 * 修改人
	 */
	private String deleter;

	/**
	 * 修改时间
	 */
	private String deleteName;


}
