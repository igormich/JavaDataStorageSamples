import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


//implementation("com.google.code.gson:gson:2.8.9")
public class JsonConverter {

    private final Gson gson = new Gson();
    private String singleUserToJson(User user) {
        return gson.toJson(user);
    }

    private User singleUserFromJson(String json) {
        return gson.fromJson(json, User.class);
    }

    private final Type userListType = new TypeToken<List<User>>() {
        /*It`s Magic*/
    }.getType();

    private String listUserToJson(List<User> users) {
        return gson.toJson(users);
    }

    private List<User> listUserFromJson(String json) {
        return gson.fromJson(json, userListType);
    }

    public static void main(String[] args) {
        var jsonConverter = new JsonConverter();
        var user = User.USERS.getFirst();
        System.out.println("Convert single user to JSON " + user);
        String jUser = jsonConverter.singleUserToJson(user);
        System.out.println("JSON " + jUser);
        var decodeUser = jsonConverter.singleUserFromJson(jUser);
        System.out.println("Decode single user from JSON " + decodeUser);
        System.out.println("Convert list of users to JSON " + User.USERS);
        String jUsers = jsonConverter.listUserToJson(User.USERS);
        System.out.println("JSON " + jUsers);
        var decodeUsers = jsonConverter.listUserFromJson(jUsers);
        System.out.println("Decode list of user from JSON " + decodeUsers);

    }

}