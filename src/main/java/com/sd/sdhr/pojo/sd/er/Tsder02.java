package com.sd.sdhr.pojo.sd.er;

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
public class Tsder02  implements Serializable {

	private static final long serialVersionUID =  7457635776593014933L;

	/**
	 * 人员项目关系号
	 */
	@TableId
	private String mpRelationNo;

	/**
	 * 人员编号
	 */
	private String memberId;

	/**
	 * 人员名称
	 */
	private String memberName;

	/**
	 * 部门
	 */
	private String deptName;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 项目地点
	 */
	private String proAddress;

	/**
	 * 项目阶段
	 */
	private String projectPhase;

	/**
	 * 负责模块
	 */
	private String resMod;

	/**
	 * 使用技术
	 */
	private String useTec;

	/**
	 * 项目经理
	 */
	private String pmName;

	/**
	 * 项目经理电话
	 */
	private String pmTel;

	/**
	 * 项目经理邮箱
	 */
	private String pmEmail;

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
