import java.io.IOException;
import java.util.List;

public interface Storage<T,E extends Throwable> {

    void save(List<T> data) throws E;

    List<T> load() throws E;
}
