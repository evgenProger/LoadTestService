package load.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Storage {
    @Getter
    private final CopyOnWriteArrayList<String> dataJson = new CopyOnWriteArrayList<>();

    public void put(String data) {
        dataJson.add(data);
    }
}
