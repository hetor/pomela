package com.pomela.scheduler.quartz.components;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by hetor on 16/3/28.
 *
 * Listener做越简单的事越好，会影响线程执行任务
 * 必须自己try-catch处理异常，否则会影响其它Listener和阻止job的执行
 */
public class SampleJobListener implements JobListener {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

    }
}
