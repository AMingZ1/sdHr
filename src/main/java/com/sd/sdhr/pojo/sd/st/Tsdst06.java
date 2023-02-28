package com.sd.sdhr.pojo.sd.st;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-12-22 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdst06  implements Serializable {

	private static final long serialVersionUID =  7708973188724670963L;

	/**
	 * 任务编号
	 */
	@TableId
	private String taskId;

	/**
	 * 任务名称
	 */
	private String taskName;

	/**
	 * 参与者
	 */
	private String taskMember;

	/**
	 * 任务状态
	 */
	private String taskStatus;

	/**
	 * 预计结束时间
	 */
	private String planEndDate;

	/**
	 * 实际结束时间
	 */
	private String actEndDate;

	/**
	 * 是否延期
	 */
	private String isPostpone;

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
