package com.sd.sdhr.constant;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: DeptColdConverter
 * @Author dems
 * @Package com.sd.sdhr.constant
 * @Date 2023/3/10 17:40
 * @description: ${description}
 */
public class AcvstatusColdConverter implements Converter<String> {

    private static Map<String,String> statusMap = new HashMap();
    static {
        statusMap.put("01", "待筛选");
        statusMap.put("02", "初筛通过");
        statusMap.put("03", "初筛未通过");
        statusMap.put("04", "面试流程中");
        statusMap.put("05", "面试未通过");
        statusMap.put("06", "已取消录用");
        statusMap.put("07", "待入职");
        statusMap.put("99", "其他");
        //归档原因：acvReason
        statusMap.put("10", "直接上传到人才库");
        statusMap.put("20", "被淘汰");
        statusMap.put("30", "去取消录用");
        statusMap.put("40", "已离职");
        statusMap.put("50", "已入职其他职位");
        statusMap.put("60", "职位关闭自动归档");
        statusMap.put("70", "初筛不通过");
    }



    @Override
    public Class supportJavaTypeKey() {

        return String.class;

    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {

        return CellDataTypeEnum.STRING;

    }

    @Override
    public String convertToJavaData(ReadCellData cellData, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String enableStatusName = cellData.getStringValue();

        for (String enableStatus : statusMap.keySet()) {

            if(StringUtils.equals(enableStatusName,statusMap.get(enableStatus))){

                return enableStatus;

            }

        }
        throw new Exception("请选择下拉框内容，不要自行修改！"+enableStatusName+"没有找到对应代码信息！");

    }


    @Override

    public WriteCellData<String> convertToExcelData(String enableStatus, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String enableStatusName = statusMap.get(enableStatus);

        return new WriteCellData(StringUtils.isBlank(enableStatusName) ? "": enableStatusName);

    }


}
