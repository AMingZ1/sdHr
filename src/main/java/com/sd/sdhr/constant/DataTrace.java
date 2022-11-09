package com.sd.sdhr.constant;

import com.sd.sdhr.constant.utils.IDataTrace;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Properties;

public class DataTrace {

    /** EiInfoUtils Bean名. */
    public static final String DATA_TRACE_CONFIG = "sd.sdhr.constant.DataTrace";
    /** EiInfoUtils默认Bean名. */
    public static final String DATA_TRACE_DEFAULT = "defaultDataTrace";

    private static Properties properties = new Properties();

    public static String getProperty(String key) {
        PropertyPlaceholderHelper propertyPlaceholderHelper = new PropertyPlaceholderHelper("${", "}");
        if (properties.containsKey(key))
            return propertyPlaceholderHelper.replacePlaceholders("${" + key + "}", properties);
        return "";
    }

}
