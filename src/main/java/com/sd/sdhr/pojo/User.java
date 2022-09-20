package com.sd.sdhr.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2022-08-16 
 */

@Data//实现get+set+toString功能
@AllArgsConstructor//有参
@NoArgsConstructor//无参
@Entity
@Table ( name ="USER" )
public class User  implements Serializable {

	private static final long serialVersionUID =  8032058676359734069L;

	/**
	 * 业务id
	 */
   	@Column(name = "BUS_ID" )
	private String busId;

	/**
	 * 人员id
	 */
   	@Column(name = "USER_ID" )
	private String userId;

	/**
	 * 人员姓名
	 */
   	@Column(name = "USER_NAME" )
	private String userName;

	/**
	 * 性别
	 */
   	@Column(name = "SEX" )
	private String sex;

	/**
	 * 人员姓名
	 */
   	@Column(name = "IS_LOCK" )
	private String isLock;

	/**
	 * 创建人
	 */
   	@Column(name = "REC_CREATOR" )
	private String recCreator;

	/**
	 * 创建人姓名
	 */
   	@Column(name = "REC_CREATE_NAME" )
	private String recCreateName;

	/**
	 * 创建人时间
	 */
   	@Column(name = "REC_CREATE_TIME" )
	private String recCreateTime;

	/**
	 * 部门代码
	 */
   	@Column(name = "DEPT_ID" )
	private String deptId;

	/**
	 * 部门名称
	 */
   	@Column(name = "DEPT_NAME" )
	private String deptName;

	/**
	 * 密码
	 */
   	@Column(name = "PASS_WORLD" )
	private String passWorld;

}
