package com.sd.sdhr.pojo.sd.er;

import java.io.Serializable;
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
public class Tsder03  implements Serializable {

	private static final long serialVersionUID =  5573153092531588037L;

	/**
	 * 人员编号
	 */
	private String memberId;

	/**
	 * 人员名称
	 */
	private String memberName;

	/**
	 * 人员类型
	 */
	private String memberType;

	/**
	 * 部门
	 */
	private String deptName;

	/**
	 * 岗位
	 */
	private String jobs;

	/**
	 * 入职日期
	 */
	private String empDate;

	/**
	 * 周访谈日期
	 */
	private String talkWeek;

	/**
	 * 月访谈日期
	 */
	private String talkMonth;

	/**
	 * 转正日期
	 */
	private String formalDate;

	/**
	 * 访谈状态
	 */
	private String talkStatus;

	/**
	 * 最近访谈时间
	 */
	private String recTalkTime;

	/**
	 * 最近提醒访谈时间
	 */
	private String remTalkTime;

	/**
	 * 入职时项目经理
	 */
	private String pmNameF;

	/**
	 * 入职时项目组信息
	 */
	private String projectNameF;

	/**
	 * 访谈计划号
	 */
	private String talkNo;

	/**
	 * 头像地址
	 */
	private String phoAddress;

	/**
	 * 技能水平
	 */
	private String skiLevel;

	/**
	 * 当前访谈进度
	 */
	private String talkPlanNow;

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
