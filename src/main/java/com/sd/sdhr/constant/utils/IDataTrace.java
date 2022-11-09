package com.sd.sdhr.constant.utils;

import java.util.List;
import java.util.Map;

public interface IDataTrace {

    /**
     * 属性常量.
     *
     */
    class Fields {

        public static final String recCreator = "recCreator";
        public static final String recCreatorName = "recCreatorName";
        public static final String recCreateTime = "recCreateTime";
        public static final String recRevisor = "recRevisor";
        public static final String recRevisorName = "recRevisorName";
        public static final String recReviseTime = "recReviseTime";
        public static final String internalCode = "internalCode";
        public static final String rowguid = "rowguid";
    }

    /**
     * setter常量.
     *
     */
    class Setters {

        public static final String setRecCreator = "setRecCreator";
        public static final String setRecCreatorName = "setRecCreatorName";
        public static final String setRecCreateTime = "setRecCreateTime";
        public static final String setRecRevisor = "setRecRevisor";
        public static final String setRecRevisorName = "setRecRevisorName";
        public static final String setRecReviseTime = "setRecReviseTime";
        public static final String setInternalCode = "setInternalCode";
    }

    // 注入失败状态
    int OP_FAILED = -1;
    // 注入成功状态
    int OP_SUCCESS = 0;
    int REC_CREATE = 1; // 0001
    int REC_REVISE = 2; // 0010
    int INNER_ID = 4; // 0100

    String NAMELESS = "nameless";

    /**
     * 根据对象的属性名，调用setter方法注入值.
     *
     */
    int invokeSetter(Object obj, String name, String value);

    /**
     * 根据key:name,value向map中注入值.
     *
     */
    int invokeSetter(Map map, String name, String value);

    /**
     * 根据不从操作类型，向Object中注入创建、修改、内码信息.
     *
     */
    int fillRec(Object base, int opLevel) throws Exception;

    /**
     * 根据操作类型，向Map中注入创建、修改、内码信息.
     *
     */
    int fillRec(Map map, int opLevel) throws Exception;

    /**
     * 根据操作类型，向List中注入创建、修改、内码信息.
     */
    int fillRec(List<Map> list, int opLevel) throws Exception;

    /**
     * 获取用户姓名.
     *
     */
    String getUserNameById(String userId);
}
