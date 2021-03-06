package com.bc.test.job.activityxxljob.jobhandler;

import com.bc.test.job.activityxxljob.config.XxlJobConfig;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.sun.org.apache.regexp.internal.RE;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ym.y
 * @description
 * @date 9:35 2022/7/10
 */
@Slf4j
@Component
public class MyJobHandler {
    @Value("${server.port}")
    private String port;

    @XxlJob(value = "firstJob")
    public void firstJob() throws Exception {
        log.info("任务开始执行");
            String jobParam = XxlJobHelper.getJobParam();
            if ("1".equals(jobParam)) {
                log.info("即将发生异常........................");
                int a = 2 / 0;
            }
            XxlJobHelper.handleSuccess("任务执行成功");
            log.info("任务执行结束.............");
    }
}
