import java.io.Serializable;

/**
 * Created by hetao on 15-7-21.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1267719235225203410L;

    private String uid;

    private String address;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "[" + uid + "," + address + "]";
    }
}
