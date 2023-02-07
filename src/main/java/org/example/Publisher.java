package org.example;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    public List<Subscriber> subscribers = new ArrayList<>();
    int messageCount = 0;

    void send(String message) {
        subscribers.forEach(subscriber -> {
            subscriber.receive(message);
            messageCount++;
        });

    }
}
