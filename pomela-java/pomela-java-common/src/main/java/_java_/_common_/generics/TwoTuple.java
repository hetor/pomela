package _java_._common_.generics;

/**
 * Created by hetor on 15/5/13.
 */
public class TwoTuple<A, B> {
    public final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
}

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}
