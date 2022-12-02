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
public class Tsder01  implements Serializable {

	private static final long serialVersionUID =  1122177988156687542L;

	/**
	 * 员工编码
	 */
	private String memberId;

	/**
	 * 员工姓名
	 */
	private String memberName;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 电话
	 */
	private String tel;

	/**
	 * 民族
	 */
	private String national;

	/**
	 * 政治面貌
	 */
	private String politStatus;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 籍贯
	 */
	private String natPlace;

	/**
	 * 户籍地址
	 */
	private String thrAddress;

	/**
	 * 居住地址
	 */
	private String resAddress;

	/**
	 * 身份证号
	 */
	private String idCard;

	/**
	 * 出生年月
	 */
	private String birthDate;

	/**
	 * 年龄
	 */
	private String age;

	/**
	 * 华夏工资卡号
	 */
	private String hxCardId;

	/**
	 * 工商银行卡号
	 */
	private String gsCardId;

	/**
	 * 血型
	 */
	private String bloodType;

	/**
	 * 婚姻状况
	 */
	private String marryStatus;

	/**
	 * 最高学历
	 */
	private String higEdu;

	/**
	 * 毕业学校
	 */
	private String universityName;

	/**
	 * 毕业时间
	 */
	private String graDate;

	/**
	 * 所学专业
	 */
	private String profession;

	/**
	 * 最高学位
	 */
	private String higDegree;

	/**
	 * 入司时间
	 */
	private String empDate;

	/**
	 * 司龄
	 */
	private String indYear;

	/**
	 * 合同编号
	 */
	private String contractNo;

	/**
	 * 合同签订时间
	 */
	private String signDate;

	/**
	 * 合同到期时间
	 */
	private String endDate;

	/**
	 * 最新结束时间
	 */
	private String endDateNew;

	/**
	 * 合同到期提醒
	 */
	private String isRemindC;

	/**
	 * 试用期开始时间
	 */
	private String probStartDate;

	/**
	 * 试用期结束时间
	 */
	private String probEndDate;

	/**
	 * 试用期到期提醒
	 */
	private String isRemindP;

	/**
	 * 是否已转正
	 */
	private String isFormal;

	/**
	 * 转正时间
	 */
	private String formalDare;

	/**
	 * 部门
	 */
	private String deptName;

	/**
	 * 岗位
	 */
	private String jobs;

	/**
	 * 社保所在地
	 */
	private String secLocation;

	/**
	 * 社保信息
	 */
	private String secInf;

	/**
	 * 属地化
	 */
	private String localInf;

	/**
	 * 紧急联系人
	 */
	private String emeContact;

	/**
	 * 与紧急联系人关系
	 */
	private String emeRel;

	/**
	 * 紧急联系人电话
	 */
	private String emeTel;

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
