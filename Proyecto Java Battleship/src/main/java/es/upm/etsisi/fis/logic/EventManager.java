package es.upm.etsisi.fis.logic;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<Subscriber> subscribers = new ArrayList<>();

    public EventManager() {
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
}
