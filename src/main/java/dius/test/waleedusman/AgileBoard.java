package dius.test.waleedusman;

import java.util.List;

public class AgileBoard {
    private Iteration iteration;

    public AgileBoard() {
        iteration = new Iteration();
    }

    public AgileBoard(List<String> columns) {
        this();
        columns.forEach(c -> iteration.addColumn(new Column(c)));
    }
}
