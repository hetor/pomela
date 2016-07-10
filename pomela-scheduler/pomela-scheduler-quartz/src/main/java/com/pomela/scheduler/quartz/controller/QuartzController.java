package com.pomela.scheduler.quartz.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.pomela.common.base.date.DateFormatUtils;
import org.pomela.common.base.date.DatePattern;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pomela.scheduler.quartz.components.QuartzManager;

/**
 * Created by hetor on 16/3/26.
 */
@Controller
public class QuartzController {

    @RequestMapping("/")
    public String index(Model model) throws SchedulerException {
        Scheduler scheduler = quartzManager.getScheduler();
        boolean isShutdown = scheduler.isShutdown();
        if (!isShutdown) {
            List<JobDetail> jobs = quartzManager.getAllJobs();
            List<Trigger> triggers = quartzManager.getAllTriggers();
            List<Trigger.TriggerState> triggerStates = new ArrayList<>(triggers.size());
            for (Trigger trigger : triggers)
                triggerStates.add(scheduler.getTriggerState(trigger.getKey()));
            List<JobKey> executingJobs = new ArrayList<>();
            List<TriggerKey> executingTriggers = new ArrayList<>();
            for (JobExecutionContext context : scheduler.getCurrentlyExecutingJobs()) {
                executingJobs.add(context.getJobDetail().getKey());
                executingTriggers.add(context.getTrigger().getKey());
            }
            model.addAttribute("jobs", jobs);
            model.addAttribute("triggers", triggers);
            model.addAttribute("triggerStates", triggerStates);
            model.addAttribute("executingJobs", executingJobs);
            model.addAttribute("executingTriggers", executingTriggers);
        }
        model.addAttribute("isShutdown", isShutdown);
        return "index";
    }

    @RequestMapping("{operate}")
    public String operate(@PathVariable String operate) throws SchedulerException {
        Scheduler scheduler = quartzManager.getScheduler();
        switch (operate) {
            case "start":
                scheduler.start();
                break;
            case "shutdown":
                scheduler.shutdown();
                break;
            case "pauseAll":
                scheduler.pauseAll();
                break;
            case "resumeAll":
                scheduler.resumeAll();
                break;
        }
        return "redirect:/";
    }

    @RequestMapping("job/{operate}")
    public String operateJob(String name, String group, @PathVariable String operate) throws SchedulerException {
        Scheduler scheduler = quartzManager.getScheduler();
        JobKey jobKey = new JobKey(name, group);
        switch (operate) {
            case "pause":
                scheduler.pauseJob(jobKey);
                break;
            case "resume":
                scheduler.resumeJob(jobKey);
                break;
            case "delete":
                scheduler.deleteJob(jobKey);
                break;
            case "trigger":
                scheduler.triggerJob(jobKey);
                break;
        }
        return "redirect:/";
    }

//    @RequestMapping(value = "job/trigger/add", method = RequestMethod.GET)
//    public void add(Model model, String name, String group) {
//        model.addAttribute(new JobKey(name, group)); // jobKey
//        model.addAttribute(TriggerType.values()); // triggerTypeList
//    }

//    @RequestMapping(value = "job/trigger/add", method = RequestMethod.POST, params = "type=Simple")
//    public String add(String jobName, String jobGroup, SimpleTriggerImpl simpleTrigger, long startDelay) throws SchedulerException {
//        simpleTrigger.setStartTime(new Date(System.currentTimeMillis() + startDelay));
//        simpleTrigger.setJobKey(new JobKey(jobName, jobGroup));
//        quartzManager.getScheduler().scheduleJob(simpleTrigger);
//        return "redirect:/";
//    }
//
//    @RequestMapping(value = "job/trigger/add", method = RequestMethod.POST, params = "type=Cron")
//    public String add(String jobName, String jobGroup, CronTriggerImpl cronTrigger, long startDelay) throws SchedulerException {
//        cronTrigger.setStartTime(new Date(System.currentTimeMillis() + startDelay));
//        cronTrigger.setJobKey(new JobKey(jobName, jobGroup));
//        quartzManager.getScheduler().scheduleJob(cronTrigger);
//        return "redirect:/";
//    }

    @RequestMapping(value = "/job/trigger/add")
    public String add(@RequestParam(required = true) String triggerName,
                      @RequestParam(required = true) String triggerGroup,
                      @RequestParam(required = true) String jobName,
                      @RequestParam(required = true) String jobGroup,
                      @RequestParam(required = true) String cronExpression,
                      String startTime,
                      String endTime) throws Exception {
        CronTriggerImpl cronTrigger = new CronTriggerImpl();
        cronTrigger.setName(triggerName);
        cronTrigger.setGroup(triggerGroup);
        cronTrigger.setCronExpression(cronExpression);
        cronTrigger.setJobKey(new JobKey(jobName, jobGroup));
//        cronTrigger.setStartTime();
//        cronTrigger.setEndTime();
        quartzManager.getScheduler().scheduleJob(cronTrigger);
        return "redirect:/";
    }

    @RequestMapping("trigger/{operate}")
    public String operateTrigger(String name, String group, @PathVariable String operate) throws SchedulerException {
        Scheduler scheduler = quartzManager.getScheduler();
        TriggerKey triggerKey = new TriggerKey(name, group);
        switch (operate) {
            case "pause":
                scheduler.pauseTrigger(triggerKey);
                break;
            case "resume":
                scheduler.resumeTrigger(triggerKey);
                break;
            case "unschedule":
                scheduler.unscheduleJob(triggerKey);
                break;
        }
        return "redirect:/";
    }

//    @RequestMapping(value = "trigger/update", method = RequestMethod.GET)
//    public void update(Model model, String name, String group) throws SchedulerException {
//        Trigger trigger = quartzManager.getScheduler().getTrigger(new TriggerKey(name, group));
//        TriggerType type = null;
//        if (trigger instanceof SimpleTriggerImpl)
//            type = TriggerType.Simple;
//        else if (trigger instanceof CronTriggerImpl)
//            type = TriggerType.Cron;
//        model.addAttribute("trigger", trigger);
//        model.addAttribute(type); // triggerType
//    }

//    @RequestMapping(value = "trigger/{type}/update", method = RequestMethod.POST)
//    public String update(@PathVariable TriggerType type, SimpleTriggerImpl simpleTrigger, CronTriggerImpl cronTrigger, long startDelay, String origName, String origGroup) throws SchedulerException {
//        Trigger trigger = null;
//        switch (type) {
//            case Simple:
//                simpleTrigger.setStartTime(new Date(System.currentTimeMillis() + startDelay));
//                trigger = simpleTrigger;
//                break;
//            case Cron:
//                cronTrigger.setStartTime(new Date(System.currentTimeMillis() + startDelay));
//                trigger = cronTrigger;
//                break;
//        }
//        if (trigger != null)
//            quartzManager.getScheduler().rescheduleJob(new TriggerKey(origName, origGroup), trigger);
//        return "redirect:/";
//    }

    @RequestMapping(value = "/job/trigger/update")
    public String update(String origTriggerName, String origTriggerGroup,
                         @RequestParam(required = false) String triggerName,
                         @RequestParam(required = false) String triggerGroup,
                      @RequestParam(required = false)String cronExpression,
                      @RequestParam(required = false) String startTime,
                      @RequestParam(required = false) String endTime) throws Exception {
        Trigger trigger = quartzManager.getScheduler().getTrigger(new TriggerKey(origTriggerName, origTriggerGroup));
        if (trigger instanceof SimpleTriggerImpl) {
            return "redirect:/";
        } else if (trigger instanceof CronTriggerImpl) {
            CronTriggerImpl cronTrigger = (CronTriggerImpl) trigger;
            cronTrigger.setName(StringUtils.defaultIfBlank(triggerName, origTriggerGroup));
            cronTrigger.setGroup(StringUtils.defaultIfBlank(triggerGroup, origTriggerGroup));
            cronTrigger.setCronExpression(StringUtils.defaultIfBlank(cronExpression, cronTrigger.getCronExpression()));
            if(StringUtils.isNotBlank(startTime)) {
                cronTrigger.setStartTime(DateFormatUtils.fromStr(startTime, DatePattern.PATTERN_7));
            }
            if(StringUtils.isNotBlank(endTime)) {
                cronTrigger.setEndTime(DateFormatUtils.fromStr(endTime, DatePattern.PATTERN_7));
            }
        }
        if(null != trigger) {
            quartzManager.getScheduler().rescheduleJob(new TriggerKey(origTriggerName, origTriggerGroup), trigger);
        }
        return "redirect:/";
    }

    @Resource
    private QuartzManager quartzManager;
}

