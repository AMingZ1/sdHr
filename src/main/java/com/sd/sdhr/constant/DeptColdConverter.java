package com.sd.sdhr.constant;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.StringUtils;
import org.flowable.engine.common.impl.util.CollectionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: DeptColdConverter
 * @Author dems
 * @Package com.sd.sdhr.constant
 * @Date 2023/3/10 17:40
 * @description: ${description}
 */
public class DeptColdConverter implements Converter<String> {

    private static Map<String,String> statusMap = new HashMap();
    static {
        statusMap.put("DEPT_001", "能环事业部");
        statusMap.put("DEPT_002", "石化事业部");
        statusMap.put("DEPT_003", "MES事业部");
        statusMap.put("DEPT_004", "智能装备事业部");
        statusMap.put("DEPT_005", "智慧城市");
        statusMap.put("DEPT_006", "自动化事业本部-研究所");
        statusMap.put("DEPT_007", "大数据服务事业部");
        statusMap.put("DEPT_008", "中铝智能铜创科技");
        statusMap.put("DEPT_009", "其烨科技");
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
