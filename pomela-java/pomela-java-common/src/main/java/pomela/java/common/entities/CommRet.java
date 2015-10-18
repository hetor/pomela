package pomela.java.common.entities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hetor on 15/10/18.
 */
public class CommRet<T> {
    public static final int SUCC = 200;
    public static final int FAIL = 400;
    public static final int ILLEGAL_PARAM = 401;
    public static final int SERVER_EXCEPTION = 402;

    private int id;
    private int code;
    private T data;
    private final Map<String, Object> extras = new HashMap<>();

    public static <E> CommRet<E> newInstance() {
        return new CommRet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }
}
