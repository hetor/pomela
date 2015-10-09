package pomela.guava.common;

import com.google.common.cache.*;
import pomela.java.common.APIAnalyzer;

import java.util.concurrent.TimeUnit;

/**
 * Created by tao.he on 2015/10/8.
 * TODO
 * http://ifeve.com/google-guava-cachesexplained/
 */
public class GuavaCacheAnalyzer implements APIAnalyzer {

	@Override
	public void doAnalysis() {
		LoadingCache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000)
				.expireAfterWrite(10, TimeUnit.MINUTES)
				.removalListener(new RemovalListener<Object, Object>() {
					@Override
					public void onRemoval(RemovalNotification<Object, Object> notification) {
						//do when removal happen
					}
				})
				.build(new CacheLoader<String, String>() {
							public String load(String key) throws Exception {
								return System.currentTimeMillis() + "_aaa";
							}
						});
	}
}
