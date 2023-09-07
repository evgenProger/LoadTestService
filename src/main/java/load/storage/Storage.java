package load.storage;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class Storage {
    @Getter
    private final CopyOnWriteArrayList<Integer> numbers = new CopyOnWriteArrayList<>();

    public void put(int number) {
        numbers.add(number);
    }
}
