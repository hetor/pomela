package com.pomela.scheduler.quartz.components;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hetor on 16/3/26.
 */
@Component
public class QuartzManager {
    public Scheduler getScheduler() {
        return scheduler;
    }

    public List<JobDetail> getAllJobs() throws SchedulerException {
        List<JobDetail> jobs = new ArrayList<>();
        if (!scheduler.isShutdown())
            for (String groupName : scheduler.getJobGroupNames())
                for (JobKey key : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName)))
                    jobs.add(scheduler.getJobDetail(key));
        return jobs;
    }

    public List<Trigger> getAllTriggers() throws SchedulerException {
        List<Trigger> triggers = new ArrayList<>();
        if (!scheduler.isShutdown())
            for (String groupName : scheduler.getTriggerGroupNames())
                for (TriggerKey key : scheduler.getTriggerKeys(GroupMatcher.triggerGroupEquals(groupName)))
                    triggers.add(scheduler.getTrigger(key));
        return triggers;
    }

    @Resource
    private Scheduler scheduler;
}
