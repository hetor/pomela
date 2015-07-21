package _java_._common_.initialize_finalize;

public class InitializeTest {

    private int a;
    private String b;
    
    @SuppressWarnings("unused")
    public void method0() {
        int c;
        String d;
//        c++;                             //error --c not initialized
//        d += "  ok!";                    //error --d not initialized
        a++; //it's ok
        b += "  ok!";
        
        
        System.out.println(a);
        System.out.println(b);
    }
}
