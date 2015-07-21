package _java_._common_.generics;

/**
 * Created by hetor on 15/5/13.
 */
public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {

    public final C third;

    public ThreeTuple(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}
