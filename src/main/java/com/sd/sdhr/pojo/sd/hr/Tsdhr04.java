package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-10-30 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdhr04  implements Serializable {

	private static final long serialVersionUID =  8599943563340064929L;

	/**
	 * 人员编号
	 */
	@TableId
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
	 * 邮tsdhr04箱
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
	 * 面试岗位
	 */
	private String itvJob;

	/**
	 * 面试部门
	 */
	private String deptName;

	/**
	 * 渠道
	 */
	private String channel;

	/**
	 * 归档原因
	 */
	private String archiveReason;

	/**
	 * 归档前状态
	 */
	private String archiveStatusbfr;

	/**
	 * 归档时间
	 */
	private String archiveDate;

	/**
	 * 工作年限
	 */
	private String workYear;

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
