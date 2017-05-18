package com.example;

import java.util.List;

public class AgileBoard {
    private final Iteration iteration;

    public AgileBoard() {
        iteration = new Iteration();
    }

    public AgileBoard(List<String> columns) {
        this();
        columns.forEach(c -> iteration.addColumn(new Column(c)));
    }

    public Iteration getIteration() {
        return iteration;
    }
}
