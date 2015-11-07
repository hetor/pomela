package pomela.java.serialize.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import pomela.java.common.utils.PrintUtil;

/**
 * Created by hetor on 15/10/25.
 *
 * An annotation that indicates this member should be serialized to JSON with the provided name value as its field name.
 *
 * This annotation will override any FieldNamingPolicy, including the default field naming policy,
 * that may have been set on the Gson instance. A different naming policy can set using the GsonBuilder class.
 * See GsonBuilder.setFieldNamingPolicy(com.google.gson.FieldNamingPolicy) for more information.
 */
public class AnnotationSerializedNameDemo {
    public static class MyClass {
        @SerializedName("name")
        String a;

        @SerializedName(value="name1", alternate={"name2", "name3"})
        String b;

        @Since(1.0)
        String c;

        public MyClass(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public String toString() {
            return "<a:" + a + " b:" + b + " c:" +c + ">";
        }
    }

    public static void main(String[] args) {
        MyClass target = new MyClass("v1", "v2", "v3");
        Gson gson = new Gson();
        String json = gson.toJson(target);
        System.out.println(json);

        MyClass target1 = gson.fromJson("{'name1':'v1'}", MyClass.class);
        PrintUtil.toConsole(target1);
        target1 = gson.fromJson("{'name2':'v2'}", MyClass.class);
        PrintUtil.toConsole(target1);
        target1 = gson.fromJson("{'name3':'v3'}", MyClass.class);
        PrintUtil.toConsole(target1);
    }
}
