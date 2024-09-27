import java.io.*;
import java.util.List;

public class SerializableStorage<T extends Serializable> implements Storage<T, Exception> {

    public void save(List<T> data) throws IOException {
        var oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("users.dat")));
        oos.writeObject(data);
        oos.flush();
        oos.close();
    }

    @SuppressWarnings("unchecked")
    public List<T> load() throws IOException {
        try (var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("users.dat")))) {
            return (List<T>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("all")
    static List<User> loadOldVersion() throws IOException, ClassNotFoundException {
        var ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("users.dat")));
        try {
            return (List<User>) ois.readObject();
        } finally {
            ois.close();
        }
    }

    public static void main(String[] args) throws IOException {

        var serializableStorage = new SerializableStorage<User>();
        System.out.println("Serialize List<String> to file " + User.USERS);
        serializableStorage.save(User.USERS);
        System.out.println("Deserialize List<String> from file " + serializableStorage.load());
    }
}
