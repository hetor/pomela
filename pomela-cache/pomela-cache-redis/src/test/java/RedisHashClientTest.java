import com.pomela.cache.redis.sdr.clients.RedisHashClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hetao on 15-7-22.
 */
@ContextConfiguration(locations = {"classpath*:spring/redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisHashClientTest {

    @Test
    public void test_updateOrAdd() {
        RedisHashClient.updateOrAdd("001", "username", "hetao");
        RedisHashClient.updateOrAdd("001", "age", "17");
        RedisHashClient.updateOrAdd("001", "address", "mo huan");
    }

    @Test
    public void test_updateOrAddMulti() {
        Map<Serializable, Serializable> user = new HashMap<>();
        user.put("username", "hetao1");
        user.put("age", "18");
        user.put("address", "mo huan.ly");
        RedisHashClient.updateOrAddMulti("002", user);
    }

    @Test
    public void test_addIfAbsent() {
        System.out.println(RedisHashClient.addIfAbsent("002", "gender", "M"));
        System.out.println(RedisHashClient.addIfAbsent("002", "username", "hetao2"));
    }

    @Test
    public void test_incrementOrAdd() {
        System.out.println(RedisHashClient.incrementOrAdd("002", "money", 10000));
    }

    @Test
    public void test_get() {
        System.out.println(RedisHashClient.get("002", "money"));
//        System.out.println(RedisHashClient.get("001", "age"));
//        System.out.println(RedisHashClient.get("001", "address"));
    }

    @Test
    public void test_getMulti() {
        List<Object> fields = new ArrayList<>();
        fields.add("username");
        fields.add("age");
        fields.add("address");
        System.out.println(RedisHashClient.getMulti("002", fields));
    }

    @Test
    public void test_values() {
        System.out.println(RedisHashClient.values("002"));
    }

    @Test
    public void test_entries() {
        System.out.println(RedisHashClient.entries("002"));
    }

    @Test
    public void test_size() {
        System.out.println(RedisHashClient.size("002"));
    }

    @Test
    public void test_fields() {
        System.out.println(RedisHashClient.fields("002"));
    }

    @Test
    public void test_hasField() {
        System.out.println(RedisHashClient.hasField("002", "username"));
        System.out.println(RedisHashClient.hasField("002", "username1"));
    }

    @Test
    public void test_delete() {
        RedisHashClient.delete("002", "gender");
    }
}
