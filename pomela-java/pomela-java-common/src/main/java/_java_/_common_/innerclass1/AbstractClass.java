package _java_._common_.innerclass1;

public abstract class AbstractClass {
    
    private int i;
    
    public AbstractClass() {}
    
    public AbstractClass(int i) {
        this.i = i;
    }
    
    public int getI() {
        return i;
    }
    
    public abstract void func();
}
