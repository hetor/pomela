package pomela.java.serialize.json.fastjson;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import java.util.Set;

/**
 * Created by hetor on 15/10/18.
 */
public class SimplePropertyPreFilterCreator extends SimplePropertyPreFilter {

    private SimplePropertyPreFilterCreator() {}

    public static SimplePropertyPreFilter newInstance(String... excludeFields) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        Set<String> excludes = filter.getExcludes();
        for (String ef : excludeFields) {
            excludes.add(ef);
        }
        return filter;
    }

    public static SimplePropertyPreFilter newInstance(Class tClass, String... excludeFields) {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(tClass);
        Set<String> excludes = filter.getExcludes();
        for (String ef : excludeFields) {
            excludes.add(ef);
        }
        return filter;
    }
}
