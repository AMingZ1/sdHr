package com.sd.sdhr.pojo.sd.er;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
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
	@ExcelProperty(value="员工编码",index = 0)
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
	@ExcelProperty(value="访谈阶段",index = 1)
	private String talkType;

	/**
	 * 跟踪时间
	 */
	@ExcelProperty(value="跟踪时间",index = 2)
	private String talkDate;

	/**
	 * 访谈状态
	 */
	@ExcelProperty(value="访谈状态",index = 3)
	private String talkStatus;

	/**
	 * 访谈内容_1
	 */
	@ExcelProperty(value="所在项目组",index = 4)
	@TableField(value="TALK_CONTENT_1")
	private String talkContent1;

	/**
	 * 访谈内容_2
	 */
	@ExcelProperty(value="公司内部评价",index = 5)
	@TableField(value="TALK_CONTENT_2")
	private String talkContent2;

	/**
	 * 访谈内容_3
	 */
	@ExcelProperty(value="项目经理评价",index = 6)
	@TableField(value="TALK_CONTENT_3")
	private String talkContent3;

	/**
	 * 访谈内容_4
	 */
	@ExcelProperty(value="身体状况",index = 7)
	@TableField(value="TALK_CONTENT_4")
	private String talkContent4;

	/**
	 * 访谈内容_5
	 */
	@ExcelProperty(value="近阶段工作强度",index = 8)
	@TableField(value="TALK_CONTENT_5")
	private String talkContent5;

	/**
	 * 访谈内容_6
	 */
	@ExcelProperty(value="工作环境与氛围",index = 9)
	@TableField(value="TALK_CONTENT_6")
	private String talkContent6;

	/**
	 * 访谈内容_7
	 */
	@ExcelProperty(value="目前负责的项目组的模块",index = 10)
	@TableField(value="TALK_CONTENT_7")
	private String talkContent7;

	/**
	 * 访谈内容_8
	 */
	@ExcelProperty(value="当前的小规划",index = 11)
	@TableField(value="TALK_CONTENT_8")
	private String talkContent8;

	/**
	 * 访谈内容_9
	 */
	@ExcelProperty(value="生活问题",index = 12)
	@TableField(value="TALK_CONTENT_9")
	private String talkContent9;

	/**
	 * 访谈内容_10
	 */
	@ExcelProperty(value="遇到困难及是否有需要帮助",index = 13)
	@TableField(value="TALK_CONTENT_10")
	private String talkContent10;

	/**
	 * 访谈内容_11
	 */
	@ExcelProperty(value="其他问题",index = 14)
	@TableField(value="TALK_CONTENT_11")
	private String talkContent11;

	/**
	 * 访谈内容_12
	 */
	@ExcelIgnore
	@TableField(value="TALK_CONTENT_12")
	private String talkContent12;

	/**
	 * 访谈内容_13
	 */
	@ExcelIgnore
	@TableField(value="TALK_CONTENT_13")
	private String talkContent13;

	/**
	 * 访谈内容_14
	 */
	@ExcelIgnore
	@TableField(value="TALK_CONTENT_14")
	private String talkContent14;

	/**
	 * 访谈内容_15
	 */
	@ExcelIgnore
	@TableField(value="TALK_CONTENT_15")
	private String talkContent15;

	/**
	 * 标准日报天数
	 */
	@ExcelIgnore
	private int dailyNum;

	/**
	 * 完成日报天数
	 */
	@ExcelIgnore
	private int dailyNumFin;

	/**
	 * 现阶段项目经理
	 */
	@ExcelProperty(value="现阶段项目经理",index = 15)
	private String pmNameNow;

	/**
	 * 项目经理反馈
	 */
	@ExcelProperty(value="项目经理反馈",index = 16)
	private String pmFeedback;

	/**
	 * 项目经理评分
	 */
	@ExcelIgnore
	private int pmScore;

	/**
	 * 是否正式员工
	 */
	@ExcelIgnore
	private String isFormal;

    /**
     * 年度
     */
	@ExcelIgnore
    private String year;

	/**
	 * 备注
	 */
	@ExcelIgnore
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
