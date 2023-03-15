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
 * @Title: ColdConverter
 * @Author dems
 * @Package com.sd.sdhr.constant
 * @Date 2023/3/10 17:40
 * @description: ${description}
 */
public class JobsColdConverter implements Converter<String> {

    private static Map<String,String> statusMap = new HashMap();
    static {
        statusMap.put("JOB_001", "JAVA开发工程师-初级");
        statusMap.put("JOB_002", "JAVA开发工程师-中级");
        statusMap.put("JOB_003", "JAVA开发工程师-高级");
        statusMap.put("JOB_004", "c++开发工程师-初级");
        statusMap.put("JOB_005", "c++开发工程师-中级");
        statusMap.put("JOB_006", "c++开发工程师-高级");
        statusMap.put("JOB_007", "前端开发-初级");
        statusMap.put("JOB_008", "前端开发-中级");
        statusMap.put("JOB_009", "前端开发-高级");
        statusMap.put("JOB_010", "自动化工程师-初级");
        statusMap.put("JOB_011", "自动化工程师-中级");
        statusMap.put("JOB_012", "自动化工程师-高级");
        statusMap.put("JOB_013", "项目经理");

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
