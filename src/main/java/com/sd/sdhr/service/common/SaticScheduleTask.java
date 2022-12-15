package com.sd.sdhr.service.common;

import com.sd.sdhr.service.sd.st.Tsdst09Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
//@EnableAsync  //多线程
public class SaticScheduleTask {
    //3.添加定时任务

    @Autowired
    Tsdst09Service tsdst09Service;


    //@Scheduled(cron = "0/5 * * * * ?")
    // 每天凌晨2:30 30执行定时任务
    @Scheduled(cron = "30 30 2 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        //System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
        tsdst09Service.insertTsdst09ByTalk();
    }

    /*@Async
    @Scheduled(fixedDelay = 2000)
    public void second() {
        System.out.println("第二个定时任务开始 : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());
        System.out.println();
    }*/


}
