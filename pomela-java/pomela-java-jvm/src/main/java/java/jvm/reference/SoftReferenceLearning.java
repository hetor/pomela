package java.jvm.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceLearning {

    public static void main(String[] args) {
        Object o = new Object();
        SoftReference<Object> softRef = new SoftReference<Object>(o);
        o = null;
        
        if(null != softRef.get()) {
            Object obj = softRef.get();
        }
    }
}
