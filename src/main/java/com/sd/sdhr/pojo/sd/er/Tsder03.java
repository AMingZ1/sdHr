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
public class Tsder03  implements Serializable {

	private static final long serialVersionUID =  5573153092531588037L;

	/**
	 * 人员编号
	 */
	@ExcelProperty(value="员工编码",index = 0)
	private String memberId;

	/**
	 * 人员名称
	 */@ExcelProperty(value="员工姓名",index = 1)
	private String memberName;

	/**
	 * 人员类型
	 */
	@ExcelProperty(value="员工类型",index = 2,converter = IsformalColdConverter.class)
	private String memberType;

	/**
	 * 部门
	 */
	@ExcelProperty(value="部门",index = 3,converter = DeptColdConverter.class)
	private String deptName;

	/**
	 * 二级事业部
	 */
	@ExcelProperty(value = "二级事业部",index = 4,converter = SecondLevelDeptColdConverter.class)
	private String secondLevelDept;

	/**
	 * 岗位
	 */
	@ExcelProperty(value="岗位",index = 5,converter = JobsColdConverter.class)
	private String jobs;

	/**
	 * 入职日期
	 */
	@ExcelProperty(value="入职日期",index = 6)
	private String empDate;

	/**
	 * 周访谈日期
	 */
	@ExcelProperty(value="周访谈日期",index = 7)
	private String talkWeek;

	/**
	 * 月访谈日期
	 */
	@ExcelProperty(value="月访谈日期",index = 8)
	private String talkMonth;

	/**
	 * 转正日期
	 */
	@ExcelProperty(value="转正日期",index = 9)
	private String formalDate;

	/**
	 * 访谈状态
	 */
	@ExcelProperty(value="访谈状态",index = 10,converter = TalkstatusColdConverter.class)
	private String talkStatus;

	/**
	 * 最近访谈时间
	 */
	@ExcelProperty(value="最近访谈时间",index = 11)
	private String recTalkTime;

	/**
	 * 最近提醒访谈时间
	 */
	@ExcelProperty(value="最近提醒访谈时间",index = 12)
	private String remTalkTime;

	/**
	 * 入职时项目经理
	 */
	@ExcelProperty(value="入职时项目经理",index = 13)
	private String pmNameF;

	/**
	 * 入职时项目组信息
	 */
	@ExcelProperty(value="入职时项目组信息",index = 14)
	private String projectNameF;

	/**
	 * 访谈计划号
	 */
	@ExcelProperty(value="访谈计划号",index = 15)
	private String talkNo;

	/**
	 * 头像地址
	 */
	@ExcelIgnore
	private String phoAddress;

	/**
	 * 技能水平
	 */
	@ExcelProperty(value="技能水平",index = 16)
	private String skiLevel;

	/**
	 * 当前访谈进度
	 */
	@ExcelProperty(value="当前访谈进度",index = 17,converter = TalkplanColdConverter.class)
	private String talkPlanNow;

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
