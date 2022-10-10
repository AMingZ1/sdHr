package com.sd.sdhr.pojo;

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
public class Tsdhr04  implements Serializable {

	private static final long serialVersionUID =  2203488139696103486L;

	/**
	 * 人才编号
	 */
	private String memberNo;

	/**
	 * 姓名
	 */
	private String memberName;

	/**
	 * 联系电话
	 */
	private String tel;

	/**
	 * 邮箱
	 */
	private String email;

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
	 * 总分
	 */
	private int sumScore;

	/**
	 * 入库主观评价
	 */
	private String evaluation;

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
