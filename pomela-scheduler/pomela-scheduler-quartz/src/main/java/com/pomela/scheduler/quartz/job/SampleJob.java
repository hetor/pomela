package com.pomela.scheduler.quartz.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by hetor on 16/3/26.
 *
 * A Job's execute method should contain a try-catch block that handles all possible exceptions.
 * If a job throws an exception, Quartz will typically immediately re-execute it (and it will likely throw the same exception again).
 * It's better if the job catches all exception it may encounter, handle them, and reschedule itself, or other jobs. to work around the issue.
 */
@Component
public class SampleJob {
    private Logger logger = LoggerFactory.getLogger(SampleJob.class);

    public void doWork() {
        logger.info("do work...");
    }
}
