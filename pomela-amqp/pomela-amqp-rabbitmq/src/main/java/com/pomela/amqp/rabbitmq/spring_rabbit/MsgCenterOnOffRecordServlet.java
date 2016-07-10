package com.pomela.amqp.rabbitmq.spring_rabbit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by hetor on 16/3/31.
 */
public class MsgCenterOnOffRecordServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig config) throws ServletException {
        MsgCenterHealthProvider.MsgCenterEnv.CONTEXT_LEVEL = HealthLevel.ALIVE;
        super.init(config);
    }

    @Override
    public void destroy() {
        MsgCenterHealthProvider.MsgCenterEnv.CONTEXT_LEVEL = HealthLevel.DEAD;
        super.destroy();
    }

}

