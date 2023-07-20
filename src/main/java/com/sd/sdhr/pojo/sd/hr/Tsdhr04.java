package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sd.sdhr.constant.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2023-02-03 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdhr04  implements Serializable {

	private static final long serialVersionUID =  2267362124669289303L;

	/**
	 * 面试记录号
	 */
	@TableId
	@ExcelProperty(value="面试记录号",index = 0)
	private String itvNo;

	/**
	 * 电联记录号
	 */
	@ExcelProperty(value="电联记录号",index = 1)
	private String planNo;

	/**
	 * 面试部门
	 */
	@ExcelProperty(value="面试部门",index = 2,converter = DeptColdConverter.class)
	private String itvDept;

	/**
	 * 二级事业部
	 */
	@ExcelProperty(value = "二级事业部",index = 3,converter = SecondLevelDeptColdConverter.class)
	private String secondLevelDept;

	/**
	 * 面试岗位
	 */
	@ExcelProperty(value="面试岗位",index = 4,converter = DeptColdConverter.class)
	private String itvJob;

	/**
	 * 面试时间
	 */
	@ExcelProperty(value="面试时间",index = 5)
	private String itvDate;

	/**
	 * 面试官
	 */
	@ExcelProperty(value="面试官",index = 6)
	private String itver;

	/**
	 * 面试方式
	 */
	@ExcelProperty(value="面试方式",index = 7,converter = OtherColdConverter.class)
	private String itvWays;

	/**
	 * 面试人员
	 */
	@ExcelProperty(value="面试人员",index = 8)
	private String memberName;

	/**
	 * 学校
	 */
	@ExcelProperty(value="学校",index = 9)
	private String universityName;

	/**
	 * 学历
	 */
	@ExcelProperty(value="学历",index = 10,converter = ExplevelColdConverter.class)
	private String educationBckr;

	/**
	 * 专业
	 */
	@ExcelProperty(value="专业",index = 11)
	private String profession;

	/**
	 * 联系电话
	 */
	@ExcelProperty(value="联系电话",index = 12)
	private String tel;

	/**
	 * 邮箱
	 */
	@ExcelProperty(value="邮箱",index = 13)
	private String email;

	/**
	 * 成绩1
	 */
	@ExcelProperty(value="沟通、表达能力(20)",index = 14)
	@TableField(value="score_1")
	private int score1;

	/**
	 * 成绩1
	 */
	@ExcelProperty(value="判断、分析、反应能力(10)",index = 15)
	@TableField(value="score_2")
	private int score2;

	/**
	 * 成绩1
	 */
	@ExcelProperty(value="责任心、纪律性(10)",index = 16)
	@TableField(value="score_3")
	private int score3;

	/**
	 * 成绩1
	 */
	@ExcelProperty(value="专业知识(30)",index = 17)
	@TableField(value="score_4")
	private int score4;

	/**
	 * 成绩1
	 */
	@ExcelProperty(value="项目经验(15)",index = 18)
	@TableField(value="score_5")
	private int score5;

	/**
	 * 成绩1
	 */
	@ExcelProperty(value="学习能力(15)",index = 19)
	@TableField(value="score_6")
	private int score6;

	/**
	 * 总分
	 */
	@ExcelProperty(value="总分",index = 20)
	private int sumScore;

	/**
	 * 面试结果
	 */
	@ExcelProperty(value="面试结果",index = 21,converter = ContastatusColdConverter.class)
	private String itvStatus;

	/**
	 * 当前状态
	 */
	@ExcelProperty(value="当前状态",index = 22,converter = OtherColdConverter.class)
	private String nowStatus;

	/**
	 * 报道时间
	 */
	@ExcelProperty(value="人员编号",index = 23)
	private String arrivalDate;

	/**
	 * 预计薪资
	 */
	@ExcelProperty(value="人员编号",index = 24)
	private BigDecimal hopeSalary;

	/**
	 * 入库主观评价
	 */
	@ExcelProperty(value="人员编号",index = 25)
	private String evaluation;

	/**
	 * 流程实例
	 */
	@ExcelIgnore
	private String processInstanceId;

	/**
	 * 备注
	 */
	@ExcelProperty(value="人员编号",index = 26)
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
