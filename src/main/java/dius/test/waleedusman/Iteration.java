package dius.test.waleedusman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Iteration {
    private static final String STARTING_COLUMN = "starting";
    private static final String DONE_COLUMN = "done";
    private static final List<String> RESERVED_COLUMN_NAMES = Arrays.asList(STARTING_COLUMN, DONE_COLUMN);

    private Map<String, Card> cards;
    // map column-name to column
    private Map<String, Column> columns;
    // map card_id -> column-name - this ensures that a card belongs to at most one column;
    private Map<String, String> cardPositions;

    private String lastMoveColumnName = "";
    private String lastMoveCardId = "";

    public Iteration() {
        cards = new HashMap<>();
        columns = new HashMap<>();
        cardPositions = new HashMap<>();
        columns.put(STARTING_COLUMN, new Column(STARTING_COLUMN));
        columns.put(DONE_COLUMN, new Column(DONE_COLUMN));
    }

    public void addCard(Card card) {
        try {
            moveCard(card, STARTING_COLUMN);
        } catch (WorkExceededException ex) {
            // "starting"-column is guaranteed to always have no 'work limit'
            // so this will never happen. Should log a message.
            return;
        }
        cards.put(card.getId(), card);
    }

    public void addColumn(Column column) {
        if (!RESERVED_COLUMN_NAMES.contains(column.getName())) {
            columns.put(column.getName(), column);
        }
    }

    public Column getColumn(String columnName) {
        return columns.get(columnName);
    }

    public String getColumnName(Card card) {
        return cardPositions.get(card.getId());
    }

    public int columnCount() {
        return columns.size();
    }

    public void undoLastMove() throws WorkExceededException {
        if (lastMoveCardId.isEmpty() || lastMoveColumnName.isEmpty()) {
            return;
        }
        Card card = cards.get(lastMoveCardId);
        Column fromColumn = columns.get(cardPositions.get(card.getId()));
        Column toColumn = columns.get(lastMoveColumnName);
        doMove(card, fromColumn, toColumn);
        lastMoveCardId = "";
        lastMoveColumnName = "";
    }

    public void moveCard(Card card, String toColumnName) throws WorkExceededException {
        Column toColumn = columns.get(toColumnName);
        if (toColumn == null) {
            throw new IllegalArgumentException();
        }
        String currentColumnName = cardPositions.get(card.getId());
        Column fromColumn = columns.get(currentColumnName);

        doMove(card, fromColumn, toColumn);
        lastMoveCardId = card.getId();
        lastMoveColumnName = currentColumnName;
    }

    private void doMove(Card card, Column fromColumn, Column toColumn) throws WorkExceededException {
        if (fromColumn != null) {
            fromColumn.addPoints(-card.getEstimate());
        }
        toColumn.addPoints(card.getEstimate());
        cardPositions.put(card.getId(), toColumn.getName());
    }

    // This is defined as the sum of the estimate of every card in the 'done' column
    public int velocity() {
        return pointsInColumn(DONE_COLUMN);
    }

    public List<Card> getCardsInColumn(String columnName) {
        return cardPositions.entrySet().stream()
                .filter(position -> position.getValue().equals(columnName))
                .map(position -> cards.get(position.getKey()))
                .collect(Collectors.toList());
    }

    private int pointsInColumn(String columnName) {
        return cardPositions.entrySet().stream()
                .filter(position -> columnName.equals(position.getValue()))
                .mapToInt(position -> cards.get(position.getKey()).getEstimate())
                .sum();
    }
}
