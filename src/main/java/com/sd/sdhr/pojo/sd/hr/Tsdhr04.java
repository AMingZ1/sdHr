package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
	private String itvNo;

	/**
	 * 电联记录号
	 */
	private String planNo;

	/**
	 * 面试部门
	 */
	private String itvDept;

	/**
	 * 面试岗位
	 */
	private String itvJob;

	/**
	 * 面试时间
	 */
	private String itvDate;

	/**
	 * 面试官
	 */
	private String itver;

	/**
	 * 面试方式
	 */
	private String itvWays;

	/**
	 * 面试人员
	 */
	private String memberName;

	/**
	 * 学校
	 */
	private String universityName;

	/**
	 * 学历
	 */
	private String educationBckr;

	/**
	 * 专业
	 */
	private String profession;

	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 成绩1
	 */
	@TableField(value="score_1")
	private int score1;

	/**
	 * 成绩1
	 */
	@TableField(value="score_2")
	private int score2;

	/**
	 * 成绩1
	 */
	@TableField(value="score_3")
	private int score3;

	/**
	 * 成绩1
	 */
	@TableField(value="score_4")
	private int score4;

	/**
	 * 成绩1
	 */
	@TableField(value="score_5")
	private int score5;

	/**
	 * 成绩1
	 */
	@TableField(value="score_6")
	private int score6;

	/**
	 * 总分
	 */
	private int sumScore;

	/**
	 * 面试结果
	 */
	private String itvStatus;

	/**
	 * 当前状态
	 */
	private String nowStatus;

	/**
	 * 报道时间
	 */
	private String arrivalDate;

	/**
	 * 预计薪资
	 */
	private BigDecimal hopeSalary;

	/**
	 * 入库主观评价
	 */
	private String evaluation;

	/**
	 * 流程实例
	 */
	private String processInstanceId;

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
