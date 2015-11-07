package pomela.java.serialize.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import pomela.java.common.utils.PrintUtil;

/**
 * Created by hetor on 15/10/25.
 *
 * Since:
 * An annotation that indicates the version number since a member or a type has been present.
 * This annotation is useful to manage versioning of your Json classes for a web-service.
 *
 * This annotation has no effect unless you build Gson
 * with a GsonBuilder and invoke GsonBuilder.setVersion(double) method.
 *
 * Until:
 * An annotation that indicates the version number until a member or a type should be present.
 * Basically, if Gson is created with a version number that exceeds the value stored in the
 * Until annotation then the field will be ignored from the JSON output. This annotation is
 * useful to manage versioning of your JSON classes for a web-service.
 *
 * This annotation has no effect unless you build Gson with a GsonBuilder and invoke GsonBuilder.
 * setVersion(double) method.
 */
public class AnnotationSinceUntilDemo {
    public static class User {
        @Until(1.0)
        private String firstName;
        @Since(1.1)
        private String lastName;
        @Since(1.2)
        @Until(1.5)
        private String emailAddress;
        @Since(1.3)
        private String password;
    }

    public static void main(String[] args) {
        User user = new User();
        user.firstName = "fn";
        user.lastName = "ln";
        user.emailAddress = "ea";
        user.password = "pw";

        Gson gson = new GsonBuilder().setVersion(1.2).create();
        PrintUtil.toConsole(gson.toJson(user));
    }
}
