package com.sd.sdhr.pojo.sd.hr;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sd.sdhr.constant.*;
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
public class Tsdhr03 implements Serializable {

	private static final long serialVersionUID =  8599943563340064929L;

	/**
	 * 人员编号
	 */
	@TableId
	@ExcelProperty(value="人员编号",index = 0)
	private String memberNo;

	/**
	 * 姓名
	 */
	@ExcelProperty(value="姓名",index = 1)
	private String memberName;

	/**
	 * 联系电话
	 */
	@ExcelProperty(value="联系电话",index = 2)
	private String tel;

	/**
	 * 邮tsdhr04箱
	 */
	@ExcelProperty(value="邮箱",index = 3)
	private String email;

	/**
	 * 学校
	 */
	@ExcelProperty(value="学校",index = 4)
	private String universityName;

	/**
	 * 学历
	 */
	@ExcelProperty(value="学历",index = 5,converter = ExplevelColdConverter.class)
	private String educationBckr;

	/**
	 * 专业
	 */
	@ExcelProperty(value="专业",index = 6)
	private String profession;

	/**
	 * 总分
	 */
	@ExcelProperty(value="总分",index = 7)
	private int sumScore;

	/**
	 * 入库主观评价
	 */
	@ExcelProperty(value="入库主观评价",index = 8)
	private String evaluation;

	/**
	 * 面试岗位
	 */
	@ExcelProperty(value="面试岗位",index = 9,converter = JobsColdConverter.class)
	private String itvJob;

	/**
	 * 面试部门
	 */
	@ExcelProperty(value="面试部门",index = 10,converter = DeptColdConverter.class)
	private String deptName;

	/**
	 * 二级事业部
	 */
	@ExcelProperty(value = "二级事业部",index = 11,converter = SecondLevelDeptColdConverter.class)
	private String secondLevelDept;

	/**
	 * 渠道
	 */
	@ExcelProperty(value="渠道",index = 12,converter = ChannelColdConverter.class)
	private String channel;

	/**
	 * 归档原因
	 */
	@ExcelProperty(value="归档原因",index = 13,converter = AcvstatusColdConverter.class)
	private String archiveReason;

	/**
	 * 归档前状态
	 */
	@ExcelProperty(value="归档前状态",index = 14,converter = AcvstatusColdConverter.class)
	private String archiveStatusbfr;

	/**
	 * 归档时间
	 */
	@ExcelProperty(value="归档时间",index = 15)
	private String archiveDate;

	/**
	 * 工作年限
	 */
	@ExcelProperty(value="工作年限",index = 16)
	private String workYear;

	/**
	 * 备注
	 */
	@ExcelProperty(value="备注",index = 17)
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
