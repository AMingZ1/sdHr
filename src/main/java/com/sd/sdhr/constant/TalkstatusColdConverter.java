package com.sd.sdhr.constant;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.StringUtils;
import com.sd.sdhr.constant.utils.SpringApplicationUtils;
import com.sd.sdhr.service.sd.st.Tsdst03Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: IsformalColdConverter
 * @Author dems
 * @Package com.sd.sdhr.constant
 * @Date 2023/3/10 17:40
 * @description: ${访谈状态}
 */
public class TalkstatusColdConverter implements Converter<String> {

    private static Map<String,String> statusMap;

    private Tsdst03Service tsdst03Service;

    /*static {
        statusMap.put("00", "已逾期");
        statusMap.put("10", "未完成");
        statusMap.put("20", "已完成");

        statusMap.put("01", "学士");
        statusMap.put("02", "硕士");
        statusMap.put("03", "博士");
    }*/

    public TalkstatusColdConverter() throws Exception{
        tsdst03Service= SpringApplicationUtils.getBean(Tsdst03Service.class);
        if (statusMap==null||statusMap.isEmpty()){
            statusMap=tsdst03Service.selectTsdst03ToMap("sdEr_talkStatus","");
        }
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
