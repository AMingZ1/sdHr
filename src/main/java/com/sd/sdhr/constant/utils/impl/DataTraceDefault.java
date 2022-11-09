package com.sd.sdhr.constant.utils.impl;

import com.sd.sdhr.constant.utils.IDataTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class DataTraceDefault implements IDataTrace {
    @Autowired
    HttpServletRequest request;

    @Override
    public int invokeSetter(Object obj, String name, String value) {
        int flag = OP_SUCCESS;
        Method setterMethod = null;
        try {
            if (obj == null) {
                return OP_FAILED;
            }

            setterMethod = obj.getClass().getMethod(name, String.class);
            setterMethod.invoke(obj, value);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException ex) {
            flag += OP_FAILED;
        }

        return flag;
    }

    @Override
    public int invokeSetter(Map map, String name, String value) {
        int flag = OP_SUCCESS;

        if (map == null) {
            return OP_FAILED;
        }

        map.put(name, value);

        return flag;
    }

    @Override
    public int fillRec(Object base, int opLevel) {
        int flag = OP_SUCCESS;

        String userName = (String) request.getSession().getAttribute("userName");
        String userId = (String) request.getSession().getAttribute("userId");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.toString();//DateUtils.curDateTimeStr14();

        // 第一位recCreate
        if ((opLevel & REC_CREATE) != 0) {
            flag += invokeSetter(base, Setters.setRecCreator, userId);
            flag += invokeSetter(base, Setters.setRecCreatorName, userName);
            flag += invokeSetter(base, Setters.setRecCreateTime, curDateTime);
            flag += invokeSetter(base, Setters.setRecRevisor, userId);
            flag += invokeSetter(base, Setters.setRecRevisorName, userName);
            flag += invokeSetter(base, Setters.setRecReviseTime, curDateTime);
        }
        // 第二位recRevise
        if ((opLevel & REC_REVISE) != 0) {
            flag += invokeSetter(base, Setters.setRecRevisor, userId);
            flag += invokeSetter(base, Setters.setRecRevisorName, userName);
            flag += invokeSetter(base, Setters.setRecReviseTime, curDateTime);
        }


        return flag;
    }

    @Override
    public int fillRec(Map map, int opLevel)  {
        int flag = OP_SUCCESS;

        String loginName = (String) request.getSession().getAttribute("userId");
        String userName = (String) request.getSession().getAttribute("userName");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDateTime = formatter.toString();//DateUtils.curDateTimeStr14();

        // 第一位recCreate
        if ((opLevel & REC_CREATE) != 0) {
            flag += invokeSetter(map, Fields.recCreator, loginName);
            flag += invokeSetter(map, Fields.recCreatorName, userName);
            flag += invokeSetter(map, Fields.recCreateTime, curDateTime);
            flag += invokeSetter(map, Fields.recRevisor, loginName);
            flag += invokeSetter(map, Fields.recRevisorName, userName);
            flag += invokeSetter(map, Fields.recReviseTime, curDateTime);
        }
        // 第二位recRevise
        if ((opLevel & REC_REVISE) != 0) {
            flag += invokeSetter(map, Fields.recRevisor, loginName);
            flag += invokeSetter(map, Fields.recRevisorName, userName);
            flag += invokeSetter(map, Fields.recReviseTime, curDateTime);
        }

        return flag;
    }

    @Override
    public int fillRec(List<Map> list, int opLevel) {
        return 0;
    }

    @Override
    public String getUserNameById(String userId) {
        return null;
    }
}
