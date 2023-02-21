package com.sd.sdhr.pojo.sd.st;

import java.io.Serializable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Description  
 * @Author  GQ
 * @Date 2023-02-21 
 */

@Data//瀹炵幇get+set+toString鍔熻兘
@AllArgsConstructor//鏈夊弬鏋勯��
@NoArgsConstructor//鏃犲弬鏋勯��
public class Tsdst12  implements Serializable {

	private static final long serialVersionUID =  7503091050533065746L;

	/**
	 * 业务单据号
	 */
	private String businessNo;

	/**
	 * 业务关键字
	 */
	private String businessKeyword;

	/**
	 * 文件转换后名称
	 */
	private String fileId;

	/**
	 * 文件名称
	 */
	private String fileName;

	/**
	 * 文件后缀
	 */
	private String fileSuffix;

	/**
	 * 文件路径
	 */
	private String filePath;

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
