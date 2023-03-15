package com.sd.sdhr.pojo.sd.hr.common;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.sd.sdhr.constant.*;
import lombok.Data;

/**
 * @Title: Tsdhr03Upload
 * @Author dems
 * @Package com.sd.sdhr.pojo.sd.hr.common
 * @Date 2023/3/14 18:04
 * @description: ${description}
 */
@Data
public class Tsdhr03Upload {

    /**
     * 姓名
     */
    @ExcelProperty(value="姓名")
    private String memberName;

    /**
     * 面试部门
     */
    @ExcelProperty(value="面试部门",converter = DeptColdConverter.class)
    private String deptName;

    /**
     * 面试岗位
     */
    @ExcelProperty(value="面试岗位",converter = JobsColdConverter.class)
    private String itvJob;

    /**
     * 联系电话
     */
    @ExcelProperty(value="联系电话")
    private String tel;

    /**
     * 邮tsdhr04箱
     */
    @ExcelProperty(value="邮箱")
    private String email;

    /**
     * 渠道
     */
    @ExcelProperty(value="渠道",converter = ChannelColdConverter.class)
    private String channel;

    /**
     * 归档原因
     */
    @ExcelProperty(value="归档原因",converter = AcvstatusColdConverter.class)
    private String archiveReason;

    /**
     * 归档前状态
     */
    @ExcelProperty(value="归档前状态",converter = AcvstatusColdConverter.class)
    private String archiveStatusbfr;

    /**
     * 工作年限
     */
    @ExcelProperty(value="工作年限")
    private String workYear;

    /**
     * 学历
     */
    @ExcelProperty(value="学历",converter = ExplevelColdConverter.class)
    private String educationBckr;

    /**
     * 备注
     */
    @ExcelProperty(value="备注")
    private String remark;





}
