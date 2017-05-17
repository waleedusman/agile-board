package dius.test.waleedusman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Iteration {
    private List<Card> cards;
    private Map<String, Column> columns;

    public Iteration() {
        cards = new ArrayList<>();
        columns = new HashMap<>();
        addColumn(new Column("starting"));
        addColumn(new Column("done"));
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void addColumn(Column column) {
        columns.put(column.getName(), column);
    }
}
