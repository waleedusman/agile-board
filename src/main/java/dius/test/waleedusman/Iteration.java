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
    // map card_id -> column-name - this ensures that a card belongs to at most one column;
    private Map<String, String> cardPosition;

    private String lastMoveColumnName = "";
    private String lastMoveCardId = "";

    public Iteration() {
        cards = new ArrayList<>();
        columns = new HashMap<>();
        cardPosition = new HashMap<>();
        addColumn(new Column(STARTING_COLUMN));
        addColumn(new Column(DONE_COLUMN));
    }

    public void addCard(Card card) {
        cards.add(card);
        cardPosition.put(card.getId(), STARTING_COLUMN);
    }

    public void addColumn(Column column) {
        columns.put(column.getName(), column);
    }

    public Column getColumn(String columnName) {
        return columns.get(columnName);
    }

    public String getColumnName(Card card) {
        return cardPosition.get(card.getId());
    }

    public int columnCount() {
        return columns.size();
    }

    public void undoLastMove() {
        if (!lastMoveCardId.isEmpty() && !lastMoveColumnName.isEmpty()) {
            cardPosition.put(lastMoveCardId, lastMoveColumnName);
        }
        lastMoveCardId = "";
        lastMoveColumnName = "";
    }

    public void moveCard(Card card, String toColumn) {
        checkColumn(toColumn);
        String currentColumnName = cardPosition.get(card.getId());

        cardPosition.put(card.getId(), toColumn);
        lastMoveCardId = card.getId();
        lastMoveColumnName = currentColumnName;
    }

    private void checkColumn(String name) {
        if (columns.get(name) == null) {
            throw new IllegalArgumentException();
        }
    }
}
