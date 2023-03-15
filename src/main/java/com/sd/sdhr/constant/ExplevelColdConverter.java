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
public class ExplevelColdConverter implements Converter<String> {

    private static Map<String,String> statusMap = new HashMap();
    static {
        //经验：sdHr_expLevel
        statusMap.put("EL001", "应届");
        statusMap.put("EL002", "一年");
        statusMap.put("EL003", "二年");
        statusMap.put("EL004", "三年");
        statusMap.put("EL005", "四年");
        statusMap.put("EL006", "五年");
        statusMap.put("EL007", "六年");
        statusMap.put("EL008", "七年");
        statusMap.put("EL009", "八年");
        statusMap.put("EL010", "九年");
        statusMap.put("EL011", "十年及以上");
        //学历：sdHr_edcBckr
        statusMap.put("01", "中专");
        statusMap.put("02", "大专");
        statusMap.put("03", "本科");
        statusMap.put("04", "本科学士");
        statusMap.put("05", "硕士研究生");
        statusMap.put("06", "博士研究生");
        statusMap.put("06", "其他");

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
