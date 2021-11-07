package Board.javafx;

import javafx.event.Event;
import java.util.EventListener;

@FunctionalInterface
public interface MyHandler<T extends Event> extends EventListener {
    boolean handle(T var1);
}
