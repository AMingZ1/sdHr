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
 * @Title: PoliticsColdConverter
 * @Author dems
 * @Package com.sd.sdhr.constant
 * @Date 2023/3/10 17:40
 * @description: ${description}
 */
public class PoliticsColdConverter implements Converter<String> {

    private static Map<String,String> statusMap = new HashMap();
    static {
        statusMap.put("0", "团员");
        statusMap.put("1", "群众");
        statusMap.put("2", "党员");
        statusMap.put("3", "中共预备党员");
        statusMap.put("4", "预备党员");
        //血型
        statusMap.put("O", "O型");
        statusMap.put("A", "A型");
        statusMap.put("B", "B型");
        statusMap.put("AB", "AB型");
        //婚姻状况
        statusMap.put("Y", "已婚");
        statusMap.put("N", "未婚");

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
