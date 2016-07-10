package com.pomela.scheduler.quartz.components;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Created by hetor on 16/3/28.
 *
 * Listener做越简单的事越好，会影响线程执行任务
 * 必须自己try-catch处理异常，否则会影响其它Listener和阻止job的执行
 */
public class SampleTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {

    }
}
