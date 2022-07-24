package com.bc.test.job.activityxxljob.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ym.y
 * @description 测试
 * @date 9:35 2022/7/10
 */
@Slf4j
@Component
public class MyJobHandler {

    @XxlJob(value = "firstJob")
    public void firstJob() {
        log.info("任务开始执行");
        try {
            String jobParam = XxlJobHelper.getJobParam();
            if ("1".equals(jobParam)) {
                log.info("即将发生异常........................");
            }
            XxlJobHelper.handleSuccess("任务执行成功");
            log.info("任务执行结束.............");
        } catch (Exception e) {
            XxlJobHelper.handleFail("任务执行失败！");
        }
    }
}
