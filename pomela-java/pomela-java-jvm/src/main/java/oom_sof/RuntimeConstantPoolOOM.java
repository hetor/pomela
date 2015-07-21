package oom_sof;

import java.util.ArrayList;

/**
 * Created by hetor on 15/4/13.
 *
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * -XX:MaxPermSize 最大方法区容量
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        //使用List保持着常量池引用，避免Full GC回收常量池行为
        ArrayList<String> list = new ArrayList<>();

        //10MB的PermSize在interger范围内足够产生OOM了
        int i=0;

        while(true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
