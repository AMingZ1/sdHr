package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;
import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sd.sdhr.constant.DeptColdConverter;
import com.sd.sdhr.constant.JobsColdConverter;
import com.sd.sdhr.constant.OtherColdConverter;
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
public class Tsdhr01  implements Serializable {

	private static final long serialVersionUID =  7458678835542504626L;

	/**
	 * 需求编号
	 */
	@TableId
	@ExcelProperty(value="岗位需求编号",index = 0)
	private String reqNo;

	/**
	 * 年份
	 */
	@ExcelProperty(value="年份",index = 1)
	private String year;

	/**
	 * 部门名称
	 */
	@ExcelProperty(value = "需求部门",index = 2,converter = DeptColdConverter.class)
	private String deptName;

	/**
	 * 岗位名称
	 */
	@ExcelProperty(value = "岗位名称",index = 3,converter = JobsColdConverter.class)
	private String itvJob;

	/**
	 * 需求数量
	 */
	@ExcelProperty(value ="需求数量",index = 4)
	private BigDecimal requireNum;

	/**
	 * 实际完成数量
	 */
	@ExcelProperty(value = "需求编号",index = 5)
	private BigDecimal realNum;

	/**
	 * 岗位要求
	 */
	@ExcelProperty(value = "岗位要求",index = 6)
	private String jobRequire;

	/**
	 * 需求联系人
	 */
	@ExcelProperty(value = "需求联系人",index = 7)
	private String requireContact;

	/**
	 * 责任人
	 */
	@ExcelProperty(value = "责任人",index = 8)
	private String dutyPerson;

	/**
	 * 预计完成实际
	 */
	@ExcelProperty(value = "预计完成实际",index = 9)
	private String planEndDate;

	/**
	 * 面试方式
	 */
	@ExcelProperty(value = "面试方式",index = 10,converter = OtherColdConverter.class)
	private String itvWays;

	/**
	 * 是否紧急
	 */
	@ExcelProperty(value = "是否紧急",index = 11)
	private String isEme;

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
	private String recCreatorName;

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
	 * 修改时间
	 */
	@ExcelIgnore
	private String recModifyName;

	/**
	 * 修改时间
	 */
	@ExcelIgnore
	private String deleteFlag;

	/**
	 * 修改时间
	 */
	@ExcelIgnore
	private String deleteTime;

	/**
	 * 修改人
	 */
	@ExcelIgnore
	private String deleter;

	/**
	 * 修改时间
	 */
	@ExcelIgnore
	private String deleteName;


}
