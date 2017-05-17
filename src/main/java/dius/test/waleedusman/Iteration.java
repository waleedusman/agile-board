package dius.test.waleedusman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Iteration {
    private static final String STARTING_COLUMN = "starting";
    private static final String DONE_COLUMN = "done";

    private List<Card> cards;
    // map column-name to column
    private Map<String, Column> columns;
    // map card to column - enforces card belongs to at most one column
    private Map<Card, Column> cardPosition;

    public Iteration() {
        cards = new ArrayList<>();
        columns = new HashMap<>();
        cardPosition = new HashMap<>();
        addColumn(new Column(STARTING_COLUMN));
        addColumn(new Column(DONE_COLUMN));
    }

    public void addCard(Card card) {
        cards.add(card);
        cardPosition.put(card, getColumn(STARTING_COLUMN));
    }

    public void addColumn(Column column) {
        columns.put(column.getName(), column);
    }

    public Column getColumn(String columnName) {
        return columns.get(columnName);
    }

    public Column getColumn(Card card) {
        return cardPosition.get(card);
    }

    public int columnCount() {
        return columns.size();
    }
}
