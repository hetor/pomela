/**
 * @Description:
 * TODO(用一句话描述该文件做什么)
 * @author hetao tao.he1989@gmail.com
 * @date 2013年12月9日下午1:32:10
 */
package _java_._common_.interface_;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Test {
    public static void main(String[] args){
        List<Thread> list = new ArrayList<>();
        for(int i=0; i<5; i++) {
            list.add(new Thread(new MyRunnable()));
        }
        for(Thread t : list) {
            t.start();
        }
    }
}

/**
 * 
 */
class MyRunnable implements Runnable {

    @Override
    public void run(){
//        synchronized (AA.class) {
//            System.out.println("---------------------------------------------");
            AA a = new AA();
            AA a1 = new AA();
            a.a("a");
            a1.a("a1");
            AA.b();
            System.out.println("c:" + AA.c++);
//        }
    }
    
}


class AA {
    public static int c = 0;
    public AA() {
        System.out.println("new AA");
    }
    
    public static void b() {
        System.out.println("static b");
    }
    
    public void a(String str){
        System.out.println(str + " in a()");
        synchronized (AA.class) {
            System.out.println(str + " begin synchronized AA.class");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(str + " end synchronized AA.class");
        }
    }
}
