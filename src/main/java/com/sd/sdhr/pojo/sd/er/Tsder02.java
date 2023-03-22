package com.sd.sdhr.pojo.sd.er;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
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
	@ExcelProperty(value="员工编码",index = 0)
	private String memberId;

	/**
	 * 人员名称
	 */
	@ExcelProperty(value="员工名称",index = 1)
	private String memberName;

	/**
	 * 部门
	 */
	@ExcelProperty(value="部门",index = 2)
	private String deptName;

	/**
	 * 项目名称
	 */
	@ExcelProperty(value="项目名称",index = 3)
	private String projectName;

	/**
	 * 项目地点
	 */
	@ExcelProperty(value="项目地点",index = 4)
	private String proAddress;

	/**
	 * 项目阶段
	 */
	@ExcelProperty(value="项目阶段",index = 5)
	private String projectPhase;

	/**
	 * 负责模块
	 */
	@ExcelProperty(value="负责模块",index = 6)
	private String resMod;

	/**
	 * 使用技术
	 */
	private String useTec;

	/**
	 * 项目经理
	 */
	@ExcelProperty(value="项目经理",index = 7)
	private String pmName;

	/**
	 * 项目经理电话
	 */
	@ExcelProperty(value="项目经理电话",index = 8)
	private String pmTel;

	/**
	 * 项目经理邮箱
	 */
	@ExcelProperty(value="项目经理邮箱",index = 9)
	private String pmEmail;

	/**
	 * 备注
	 */
	@ExcelProperty(value="备注",index = 10)
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
