import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


//implementation("org.xerial:sqlite-jdbc:3.46.1.0")
public class RawDatabase implements Storage<User, SQLException>{

    private Connection conn = null;
    public RawDatabase() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
        Statement statement = conn.createStatement();
        String sql = """
                    CREATE TABLE IF NOT EXISTS users (
                     id integer PRIMARY KEY,
                     name text NOT NULL,
                     age integer
                    );""";
        statement.execute(sql);

    }


    public void save(List<User> users) throws SQLException {
        for(var user : users) {
            //Dont use
            //String sql = "INSERT INTO users (id, name, age) VALUES ("+ user.age +", "+ user.name +", "+ user.age+")";
            String sql = "INSERT INTO users (id, name, age) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setInt(3, user.getAge());
            pstmt.executeUpdate();
        }
    }
    public List<User> load() throws SQLException {
        Statement statement = conn.createStatement();
        var sql = "SELECT id, name, age FROM users";
        ResultSet rs = statement.executeQuery(sql);
        var result = new ArrayList<User>();
        while (rs.next()) {
            result.add(new User(rs.getInt("id"), rs.getString("name"), rs.getInt("age")));
        }
        return result;
    }
    public static void main(String[] args) throws IOException, SQLException {
        Files.delete(new File("sample.db").toPath());
        var database = new RawDatabase();
        System.out.println("Store List<String> in database " + User.USERS);
        database.save(User.USERS);
        System.out.println("Load List<String> from database " + database.load());
    }
}
