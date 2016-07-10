package com.pomela.amqp.rabbitmq.spring_rabbit;

import javax.annotation.Resource;

import com.pomela.amqp.rabbitmq.spring_rabbit.entities.Msg;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pomela.amqp.rabbitmq.spring_rabbit.producers.SampleProducer;

import java.io.IOException;
import java.util.Date;

/**
 * Created by hetor on 16/3/31.
 */
@Controller
@RequestMapping("/produce")
public class ProducerController {

    @Resource
    private SampleProducer sampleProducer;

    @Resource(name = "queueListenerPool")
    private ThreadPoolTaskExecutor  threadPoolTaskExecutor;


    @RequestMapping("")
    @ResponseBody
    public String produce() throws IOException {
        System.out.println(new Date());
        for (int i=0; i<10; i++) {
            Msg msg = new Msg();
            msg.setId(System.currentTimeMillis());
            msg.setName(System.currentTimeMillis() + "_name");
            msg.setPassword(System.currentTimeMillis() + "_pwd");
            sampleProducer.send(msg);
        }
        return "success";
    }

    @RequestMapping("/getPoolState")
    @ResponseBody
    public String getPoolState() {
        int activeCount = threadPoolTaskExecutor.getActiveCount();
        int corePoolSize = threadPoolTaskExecutor.getCorePoolSize();
        int maxPoolSize = threadPoolTaskExecutor.getMaxPoolSize();
        int poolSize = threadPoolTaskExecutor.getPoolSize();
        int keepAliveSeconds = threadPoolTaskExecutor.getKeepAliveSeconds();

        long completedTaskCount = threadPoolTaskExecutor.getThreadPoolExecutor().getCompletedTaskCount();
        int remainingCapacity = threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().remainingCapacity();
        int size = threadPoolTaskExecutor.getThreadPoolExecutor().getQueue().size();
        long taskCount = threadPoolTaskExecutor.getThreadPoolExecutor().getTaskCount();

        return new StringBuilder().append("pool activeCount:").append(activeCount).append("\n")
                .append("pool corePoolSize:").append(corePoolSize).append("\n")
                .append("pool maxPoolSize:").append(maxPoolSize).append("\n")
                .append("poolSize:").append(poolSize).append("\n")
                .append("pool keepAliveSeconds:").append(keepAliveSeconds).append("\n")
                .append("pool completedTaskCount:").append(completedTaskCount).append("\n")
                .append("pool taskCount:").append(taskCount).append("\n")
                .append("queue size:").append(size).append("\n")
                .append("queue remainingCapacity:").append(remainingCapacity).append("\n")
                .toString();
    }
}
