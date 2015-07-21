package jmx.mx4j.some;

import mx4j.tools.adaptor.http.HttpAdaptor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;

import javax.annotation.Resource;
import java.io.IOException;

public class Mx4jHttpAdaptorStarter implements SmartInitializingSingleton {
//	@Resource(name="httpAdaptor")
	private HttpAdaptor httpAdaptor;

    public HttpAdaptor getHttpAdaptor() {
        return httpAdaptor;
    }

    public void setHttpAdaptor(HttpAdaptor httpAdaptor) {
        this.httpAdaptor = httpAdaptor;
    }

    @Override
    public void afterSingletonsInstantiated() {
        try {
            httpAdaptor.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}