import com.pomela.cache.redis.sdr.clients.RedisCommonClient;
import com.pomela.cache.redis.sdr.clients.RedisStringClient;
import com.sun.xml.internal.ws.developer.UsesJAXBContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by hetao on 15-7-21.
 */
@ContextConfiguration(locations = {"classpath*:spring/redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisStringClientTest {

    @Test
    public void test_updateOrAdd() {
        User user = new User();
        user.setUid("1234561");
        user.setAddress("mo huan");

        RedisStringClient.updateOrAdd(user.getUid(), user);
    }

    @Test
    public void test_updateOrAddMulti() {
        Map<String, Serializable> users = new HashMap<>();

        for(int i=0; i<5; i++) {
            User user = new User();
            user.setUid("123456" + i);
            user.setAddress("mo huan" + i);
            users.put(user.getUid(), user);
        }

        RedisStringClient.updateOrAddMulti(users);
    }

    @Test
    public void test_readMulti() throws InterruptedException {
        List<Serializable> keys = new ArrayList<>();

        for(int i=0; i<5; i++) {
            keys.add("123456" + i);
        }

        while(true) {
            List<Serializable> users = RedisStringClient.readMulti(keys);
            System.out.println(users);
            if(null == users || users.isEmpty())
                break;
            TimeUnit.SECONDS.sleep(5);
        }
    }

    @Test
    public void test_read() throws InterruptedException {
        while(true) {
            User user = (User) RedisStringClient.read("123456");
            System.out.println(user);
            if(null == user)
                break;
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
