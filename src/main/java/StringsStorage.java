import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

public class StringsStorage implements Storage<String, IOException> {
    @Override
    public void save(List<String> data) throws IOException {
        Files.write(new File("data.txt").toPath(), data, Charset.defaultCharset());
    }
    @Override
    public List<String> load() throws IOException {
        return Files.readAllLines(new File("data.txt").toPath(), Charset.defaultCharset());
    }

    public static void main(String[] args) throws IOException {
        var stringsStorage = new StringsStorage();
        var list = List.of("123", "ABC", "Привет");
        System.out.println("Write List<String> to file " + list);
        stringsStorage.save(list);
        System.out.println("List<String> has been read from file " + stringsStorage.load());
    }
}
