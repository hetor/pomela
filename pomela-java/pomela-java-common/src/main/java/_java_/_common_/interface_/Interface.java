/**
 * 
 */
package _java_._common_.interface_;

/**
 * @Description:
 * interface knowledges
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月3日下午7:41:43
 */
public class Interface{
    // do nothing
}

/**interface support public or default but not protected and private**/
interface IA {
    void f1();
}

/**protected is not allowed**/
//protected interface IA {
//}

/**private is not allowed**/
//private interface IA{
//} 

interface IB {
//    public final static String YEAR; //must init
    String MONTH = "December"; // fields in a interface is 'public final static' by default
    void f1(); //public by default
    public String f2();
}

/**class support public or default but not protected and private**/
class C {
    int f1() {
        return 0;
    }
}

/**interface supports multi-inheritance**/
interface IAB extends IA, IB {
    @Override
    public void f1(); //it's ok for the same method
}

/**
 * can implements multi-interfaces, but only one class
 * class D error because IA, IB and C are incompatible, the return type of method f1() is different
 */
//class D extends C implements IA, IB {
//    
//}