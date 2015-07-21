package _java_._common_.initialize_finalize;

/**
 * java initialize
 * @author hetao
 */
public class InitializeTest1 {
    public static void main(String[] args) {
        new ChildClass();
    }
}

class Sample {
    Sample(String s) {
        System.out.println(s);
    }
    Sample() {
        this("Sample's default constructor !");
    }
}

class SuperClass{
    static{
        System.out.println("execute static block1 of super class !");
    }
    static Sample staticSam1 = new Sample("init static member staticSam1 of super class !");
    Sample sam1 = new Sample("init member sam1 of super class !");
    {
        System.out.println("execute block of super class !");
    }
    static Sample staticSam2 = new Sample("init static member staticSam2 of super class !");
    static{
        System.out.println("execute static block2 of super class !");
    }
    SuperClass() {
        System.out.println("execute default constructor of super class !");
    }
    Sample sam2 = new Sample("init member sam2 of super class !");
}

class ChildClass extends SuperClass{
    static Sample staticSam1 = new Sample("init static member staticSam1 for child class !");
    ChildClass() {
        System.out.println("execute default constructor of child class !");
    }
    Sample sam1 = new Sample("init member sam1 of child class !");
    {
        System.out.println("execute block of child class !");
    }
    static Sample staticSam2 = new Sample("init static member staticSam2 for child class !");
    static {
        System.out.println("execute static block of child class !");
    }
    Sample sam2 = new Sample("init member sam2 of child class !");
}
