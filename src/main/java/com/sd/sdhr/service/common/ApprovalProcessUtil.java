package com.sd.sdhr.service.common;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class ApprovalProcessUtil {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    public String apply(String taskId,String userId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("isFlag", "Y");
        map.put("userId", userId);
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




}
