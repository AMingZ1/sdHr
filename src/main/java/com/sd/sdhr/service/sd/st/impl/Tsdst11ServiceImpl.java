package com.sd.sdhr.service.sd.st.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sd.sdhr.mapper.sd.st.Tsdst11Mapper;
import com.sd.sdhr.pojo.sd.st.Tsdst11;
import com.sd.sdhr.pojo.sd.st.Tsdst12;
import com.sd.sdhr.service.sd.st.Tsdst11Service;
import liquibase.pro.packaged.E;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Tsdst11ServiceImpl
 * @Author dems
 * @Package com.sd.sdhr.service.sd.st.impl
 * @Date 2023/2/22 10:36
 * @description: ${description}
 */
@Slf4j
@Service
public class Tsdst11ServiceImpl implements Tsdst11Service {

    @Autowired
    Tsdst11Mapper tsdst11Mapper;

    @Override
    public List<Tsdst11> getAllTsdst11(Tsdst11 tsdst11) {
        List<Tsdst11> list =new ArrayList();
        try {
            QueryWrapper<Tsdst11> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq(!StringUtils.isEmpty(tsdst11.getRoleCode()),"ROLE_CODE",tsdst11.getRoleCode());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst11.getMemberId()),"MEMBER_ID",tsdst11.getMemberId());
            queryWrapper.eq(!StringUtils.isEmpty(tsdst11.getMemberName()),"MEMBER_NAME",tsdst11.getMemberName());

            list=tsdst11Mapper.selectList(queryWrapper);

        }catch (Exception e){
            log.error("{}",e);
        }
        return list;
    }

    @Override
    public List<Tsdst11> getAllTsdst11(String roleCode) {
        List<Tsdst11> list =new ArrayList();
        try {
            if(StringUtils.isEmpty(roleCode)){
                throw new Exception("角色代码为空！");
            }
            QueryWrapper<Tsdst11> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("ROLE_CODE",roleCode);
            list=tsdst11Mapper.selectList(queryWrapper);

        }catch (Exception e){
            log.error("{}",e);
        }
        return list;
    }
}
