/**
 * @Description:
 * 本类演示了Arrays类中的asList方法 
 * 通过四个段落来演示,体现出了该方法的相关特性. 
 *  
 * (1) 该方法对于基本数据类型的数组支持并不好,当数组是基本数据类型时不建议使用 
 * (2) 当使用asList()方法时，数组就和列表链接在一起了. 
 *     当更新其中之一时，另一个将自动获得更新。 
 *     注意:仅仅针对对象数组类型,基本数据类型数组不具备该特性 
 * (3) asList得到的数组是的没有add和remove方法的 
 *  
 * 阅读相关:通过查看Arrays类的源码可以知道,asList返回的List是Array中的实现的 
 * 内部类,而该类并没有定义add和remove方法.另外,为什么修改其中一个,另一个也自动 
 * 获得更新了,因为Arrays.asList(a)直接使用数组a作为物理存储 
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月5日下午2:32:04
 */
package java.collections.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysAsListTester {

    public static void main(String[] args) {
        
        String a[] = {"1", "2"};
        int b[] = {1,2,3,4,5,6};
        /*
         * 返回的List是Arrays$ArrayList对象
         * new aList refs to array a
         * add(), remove() not supported,
         */
        List<String> aList = Arrays.asList(a);
        /*
         * java.util.ArrayList new bList by clone the elements in aList<Collection>
         */
        List<String> list = new ArrayList<>(aList);
        
        System.out.println("aList: " + aList);
        System.out.println("list: " + list);
        a[0] = "changed";
        System.out.println("array a changed!!!");
        System.out.println("aList: " + aList);
        System.out.println("list: " + list);
        
        //把int数组当成一个对象
        List<int[]> bList = Arrays.asList(b);
        System.out.println(bList);
        
        aList.add("3"); //operation not supported in Arrays&ArrayList
    }
}
