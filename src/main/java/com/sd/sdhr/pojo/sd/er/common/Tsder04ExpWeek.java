package com.sd.sdhr.pojo.sd.er.common;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Title: Tsdhr04ExpWeek
 * @Author dems
 * @Package com.sd.sdhr.pojo.sd.er.common
 * @Date 2023/3/22 17:11
 * @description: ${description}
 */
@Data
public class Tsder04ExpWeek {
    /**
     * 人员编号
     */
    @TableId
    @ExcelProperty(value="员工编码",index = 0)
    private String memberId;

    /**
     * 访谈计划号
     */
    @TableId
    private String talkNo;

    /**
     * 访谈阶段
     */
    @TableId
    @ExcelProperty(value="访谈阶段",index = 1)
    private String talkType;

    /**
     * 跟踪时间
     */
    @ExcelProperty(value="跟踪时间",index = 2)
    private String talkDate;

    /**
     * 访谈状态
     */
    @ExcelProperty(value="访谈状态",index = 3)
    private String talkStatus;

    /**
     * 访谈内容_1
     */
    @ExcelProperty(value="公司内部评价",index = 4)
    private String talkContent1;

    /**
     * 访谈内容_2
     */
    @ExcelProperty(value="项目经理评价",index = 5)
    private String talkContent2;

    /**
     * 访谈内容_3
     */
    @ExcelProperty(value="入职后对公司整体印象与面试沟通是否反差举例说明",index = 6)
    private String talkContent3;

    /**
     * 访谈内容_4
     */
    @ExcelProperty(value="对公司基本情况、福利、管理制度不清楚的点",index = 7)
    private String talkContent4;

    /**
     * 访谈内容_5
     */
    @ExcelProperty(value="工作氛围感受",index = 8)
    private String talkContent5;

    /**
     * 访谈内容_6
     */
    @ExcelProperty(value="对目前工作环境适应和满意度",index = 9)
    private String talkContent6;

    /**
     * 访谈内容_7
     */
    @ExcelProperty(value="项目经理进行工作安排方面怎么样",index = 10)
    private String talkContent7;

    /**
     * 访谈内容_8
     */
    @ExcelProperty(value="对岗位的了解",index = 11)
    private String talkContent8;

    /**
     * 访谈内容_9
     */
    @ExcelProperty(value="遇到困难及是否需要帮助的",index = 12)
    private String talkContent9;

    /**
     * 访谈内容_10
     */
    @ExcelProperty(value="其他问题",index = 13)
    private String talkContent10;

    /**
     * 访谈内容_11
     */
    @ExcelIgnore
    private String talkContent11;

    /**
     * 访谈内容_12
     */
    @ExcelIgnore
    private String talkContent12;

    /**
     * 访谈内容_13
     */
    @ExcelIgnore
    private String talkContent13;

    /**
     * 访谈内容_14
     */
    @ExcelIgnore
    private String talkContent14;

    /**
     * 访谈内容_15
     */
    @ExcelIgnore
    private String talkContent15;

    /**
     * 标准日报天数
     */
    @ExcelProperty(value="标准日报天数",index = 14)
    private int dailyNum;

    /**
     * 完成日报天数
     */
    @ExcelProperty(value="完成日报天数",index = 15)
    private int dailyNumFin;

    /**
     * 现阶段项目经理
     */
    @ExcelProperty(value="现阶段项目经理",index = 16)
    private String pmNameNow;

    /**
     * 项目经理反馈
     */
    @ExcelProperty(value="项目经理反馈",index = 17)
    private String pmFeedback;

    /**
     * 项目经理评分
     */
    @ExcelIgnore
    private int pmScore;

    /**
     * 是否正式员工
     */
    @ExcelIgnore
    private String isFormal;

    /**
     * 年度
     */
    @ExcelIgnore
    private String year;

    /**
     * 备注
     */
    @ExcelIgnore
    private String remark;



}
