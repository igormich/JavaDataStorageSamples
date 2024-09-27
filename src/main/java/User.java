import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    public static List<User> USERS = List.of(new User(1, "Вася", 15),
            new User(2, "Петя", 17),
            new User(3, "Оля", 20));

    private final int id;
    private final String name;
    private final int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
