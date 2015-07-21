package oom_sof;

import java.util.ArrayList;

/**
 * Created by hetor on 15/4/13.
 *
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * 提示：将堆的最小值-Xms和最大值-Xmx设置为一样可避免堆自动扩展
 * 通过参数-XX:+HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆转储快照以便事后进行分析
 */
public class HeapOOM {

    static class OOMObject {

    }

    public static  void main(String[] args) {
        ArrayList<OOMObject> list = new ArrayList<>();

        while(true) {
            list.add(new OOMObject());
        }
    }
}
