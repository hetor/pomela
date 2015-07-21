package oom_sof;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by hetor on 15/4/13.
 *
 * VM Args: -Xmx20M -XX:MaxDirectMemorySie=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024*1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
