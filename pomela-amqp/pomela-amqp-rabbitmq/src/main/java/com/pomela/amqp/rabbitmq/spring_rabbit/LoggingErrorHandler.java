package com.pomela.amqp.rabbitmq.spring_rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

/**
 * Created by hetor on 16/3/31.
 */
@Component("loggingErrorHandler")
public class LoggingErrorHandler implements ErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoggingErrorHandler.class);

    @Override
    public void handleError(Throwable t) {
        logger.error("LoggingErrorHandler:" + t.getMessage(), t);
    }
}