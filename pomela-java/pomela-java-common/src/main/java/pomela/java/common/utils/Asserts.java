package pomela.java.common.utils;

/**
 * Created by hetor on 15/10/18.
 */
public class Asserts {
    private Asserts(){}

    public static void isTrue(boolean expression) {
        isTrue(expression, "assert is not true");
    }

    public static void isTrue(boolean expression, String errMess) {
        if(!expression) {
            throw new RuntimeException(errMess);
        }
    }
}
