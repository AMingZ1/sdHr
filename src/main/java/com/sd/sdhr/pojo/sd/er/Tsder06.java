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
public class Tsder06  implements Serializable {

	private static final long serialVersionUID =  6464808017109436616L;

	/**
	 * 年度
	 */
	private String year;

	/**
	 * 上半年访谈节点
	 */
	private String node1;

	/**
	 * 下半年访谈节点
	 */
	private String node2;

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
