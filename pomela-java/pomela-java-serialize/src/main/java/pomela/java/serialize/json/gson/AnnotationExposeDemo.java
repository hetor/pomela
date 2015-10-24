package pomela.java.serialize.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import pomela.java.common.utils.PrintUtil;
import pomela.java.serialize.json.GsonJsonUtil;

import java.lang.reflect.Modifier;

/**
 * Created by tao.he on 2015/10/24.
 */
public class AnnotationExposeDemo {

	public static class User {
		@Expose
		private String firstName;

		@Expose(serialize = false)
		private String lastName;

		@Expose (serialize = false, deserialize = false)
		private String emailAddress;

		private String password;
	}

	public static void main(String[] args) {
		User user = new User();
		user.firstName = "he";
		user.lastName = "tao";
		user.emailAddress = "tao.he@gmail.com";
		user.password = "123456";

		/**
		 * if you created Gson with new Gson(), the toJson() and fromJson() methods will use the password field
		 * along-with firstName, lastName, and emailAddress for serialization and deserialization.
		 */
		Gson gson = new Gson();
		PrintUtil.toConsole(gson.toJson(user));

		/**
		 * However, if you created Gson with Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
		 * then the toJson() and fromJson() methods of Gson will exclude the password field.
		 * This is because the password field is not marked with the @Expose annotation.
		 *
		 * Gson will also exclude lastName and emailAddress from serialization since serialize is set to false.
		 * Similarly, Gson will exclude emailAddress from deserialization since deserialize is set to false.
		 */
		Gson gson1 = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		PrintUtil.toConsole(gson1.toJson(user));

		/**
		 * Note that another way to achieve the same effect would have been to just mark the password field as transient,
		 * and Gson would have excluded it even with default settings.
		 *
		 * The @Expose annotation is useful in a style of programming where you want to explicitly specify all fields
		 * that should get considered for serialization or deserialization.
		 */
	}
}




