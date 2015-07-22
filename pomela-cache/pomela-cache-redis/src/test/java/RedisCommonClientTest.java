import com.pomela.cache.redis.sdr.clients.RedisCommonClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hetao on 15-7-22.
 */
@ContextConfiguration(locations = {"classpath*:spring/redis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisCommonClientTest {

    @Test
    public void test_delete() {
        RedisCommonClient.delete("002");
    }
}
