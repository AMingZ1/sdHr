package com.sd.sdhr.service.common;

import com.sd.sdhr.pojo.sd.st.Tsdst11;
import com.sd.sdhr.service.sd.st.Tsdst11Service;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApprovalProcessUtil {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private Tsdst11Service tsdst11Service;

    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    public String apply(String taskId,String userId,String roleCode) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过用户组获取用户组对应人员信息
        List<Tsdst11> st11List=tsdst11Service.getAllTsdst11(roleCode);
        if (st11List.size()==0){
            throw new RuntimeException("通过角色代码"+roleCode+"没有获取到人员信息！");
        }
        List<String> idList=new ArrayList();
        st11List.forEach(st11->{
            idList.add(st11.getMemberId());
        });
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("isFlag", "Y");
        map.put("userIdList", idList);
        taskService.complete(taskId, map);
        return "1";
    }

    /**
     * 拒绝
     */
    public String reject(String taskId,String userId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("isFlag", "N");
        map.put("userId", userId);
        taskService.complete(taskId, map);
        return "1";
    }

    /**
     * 添加报销
     *
     * @param userId    用户Id
     * @param processDefinitionKey 流程KEY
     */
    public String addExpense(String userId, String processDefinitionKey) {
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, map);
        String processInstanceId=processInstance.getId();
        String an=processInstance.getProcessInstanceId();
        List<Task> tasks2=taskService.createTaskQuery().taskAssignee(userId).processInstanceId(processInstanceId).list();
        String taskId=tasks2.get(0).getId();// 拿到任务Id
        //通过审核
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("isFlag", "Y");
        map3.put("userId", userId);// 下级审批人
        taskService.complete(taskId, map3);

        return processInstanceId;
    }




}
