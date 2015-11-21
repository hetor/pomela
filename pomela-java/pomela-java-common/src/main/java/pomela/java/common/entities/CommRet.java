package pomela.java.common.entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hetor on 15/10/18.
 */
public class CommRet implements Serializable {
    private static final long serialVersionUID = -6174773662523042775L;

    public static final int SUCCESS_CODE = 200;

    public static final int FAIL_CODE = 400;

    public static final int PARAM_INVALID_CODE = 401;

    public static final int SERVER_SIDE_EXCEPTION_CODE = 500;

    public static final String TOTAL = "total";
    public static final String LIST = "list";


    private int code;

    private String msg;

    private Map<String, Object> data;


    public int getCode() {
        return code;
    }

    public CommRet setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public CommRet setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public CommRet addData(Map<String, Object> data) {
        for (String key : data.keySet()) {
            addData(key, data.get(key));
        }
        return this;
    }

    public CommRet addData(String key, Object value) {
        if (null == data) {
            data = new HashMap<>();
        }
        data.put(key, value);
        return this;
    }

    public static CommRet succ() {
        CommRet ret = new CommRet();
        return ret.setCode(SUCCESS_CODE);
    }

    public static CommRet succ(String msg) {
        return CommRet.succ().setMsg(msg);
    }

    public static CommRet succ(String key, Object value) {
        return CommRet.succ().addData(key, value);
    }

    public static CommRet succ(Map<String, Object> data) {
        return CommRet.succ().addData(data);
    }

    public static CommRet succ(int total, List list) {
        CommRet ret = succ();
        ret.addData(TOTAL, total);
        ret.addData(LIST, list);
        return ret;
    }

    public static CommRet fail(int errCode) {
        CommRet ret = new CommRet();
        return ret.setCode(errCode);
    }

    public static CommRet fail(int errCode, String errMsg) {
        return CommRet.fail(errCode).setMsg(errMsg);
    }

    public boolean isSucc() {
        return code == 200;
    }
}
