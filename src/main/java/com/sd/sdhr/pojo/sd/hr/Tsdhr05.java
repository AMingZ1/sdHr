package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;
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
public class Tsdhr05  implements Serializable {

	private static final long serialVersionUID =  2564987112005987576L;

	/**
	 * 员工编码
	 */
	private String memberId;

	/**
	 * 员工姓名
	 */
	private String memberName;

	/**
	 * 员工阶段
	 */
	private String workPhase;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 入职时间
	 */
	private String empDate;

	/**
	 * 合同签订时间
	 */
	private String signDate;

	/**
	 * 合同到期时间
	 */
	private String endDate;

	/**
	 * 访谈计划号
	 */
	private String intNo;

	/**
	 * 项目计划号
	 */
	private String proNo;

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
