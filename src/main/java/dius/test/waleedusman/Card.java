package dius.test.waleedusman;

import java.util.UUID;

public class Card {
    private final String id;
    private final String title;
    private final String description;
    private final int estimate;

    public Card(String title, String description, int estimate) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.estimate = estimate;
    }

    public String getId() {
        return id;
    }
}
