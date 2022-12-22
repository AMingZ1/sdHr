package com.sd.sdhr.pojo.sd.st;

import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-12-14 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdst09  implements Serializable {

	private static final long serialVersionUID =  2506617146223927336L;

	/**
	 * 消息ID
	 */
	private String messageId;

	/**
	 * 人员编号
	 */
	private String memberId;

	/**
	 * 人员编号
	 */
	private String memberName;

	/**
	 * 业务单据号
	 */
	private String businessNo;

	/**
	 * 业务类型
	 */
	private String businessType;

	/**
	 * 消息说明
	 */
	private String messageRemark;

	/**
	 * 消息状态
	 */
	private String messageStatus;

	/**
	 * 消息url
	 */
	private String messageUrl;

	/**
	 * 年度
	 */
	private String year;

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
