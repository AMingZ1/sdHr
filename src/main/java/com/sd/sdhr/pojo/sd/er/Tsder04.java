package com.sd.sdhr.pojo.sd.er;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-10-31 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsder04  implements Serializable {

	private static final long serialVersionUID =  1231822132231375450L;

	/**
	 * 人员编号
	 */
	@TableId
	private String memberId;

	/**
	 * 访谈计划号
	 */
	@TableId
	private String talkNo;

	/**
	 * 访谈阶段
	 */
	@TableId
	private String talkType;

	/**
	 * 跟踪时间
	 */
	private String talkDate;

	/**
	 * 访谈内容_1
	 */
	private String talkContent1;

	/**
	 * 访谈内容_2
	 */
	private String talkContent2;

	/**
	 * 访谈内容_3
	 */
	private String talkContent3;

	/**
	 * 访谈内容_4
	 */
	private String talkContent4;

	/**
	 * 访谈内容_5
	 */
	private String talkContent5;

	/**
	 * 访谈内容_6
	 */
	private String talkContent6;

	/**
	 * 访谈内容_7
	 */
	private String talkContent7;

	/**
	 * 访谈内容_8
	 */
	private String talkContent8;

	/**
	 * 访谈内容_9
	 */
	private String talkContent9;

	/**
	 * 访谈内容_10
	 */
	private String talkContent10;

	/**
	 * 访谈内容_11
	 */
	private String talkContent11;

	/**
	 * 访谈内容_12
	 */
	private String talkContent12;

	/**
	 * 访谈内容_13
	 */
	private String talkContent13;

	/**
	 * 访谈内容_14
	 */
	private String talkContent14;

	/**
	 * 访谈内容_15
	 */
	private String talkContent15;

	/**
	 * 标准日报天数
	 */
	private int dailyNum;

	/**
	 * 完成日报天数
	 */
	private int dailyNumFin;

	/**
	 * 现阶段项目经理
	 */
	private String pmNameNow;

	/**
	 * 项目经理反馈
	 */
	private String pmFeedback;

	/**
	 * 项目经理评分
	 */
	private int pmScore;

	/**
	 * 是否正式员工
	 */
	private String isFormal;

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
