package dius.test.waleedusman;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class IterationTest {
    @Test
    public void shouldHaveTwoColumnsWhenCreateIteration() {
        Iteration iteration = new Iteration();
        assertThat(iteration.columnCount(), is(equalTo(2)));
        assertThat(iteration.getColumn("starting"), is(notNullValue()));
        assertThat(iteration.getColumn("done"), is(notNullValue()));
    }

    // Assuming every card must belong to a column
    @Test
    public void shouldPutCardInStartingColumnWhenAddedToIteration() {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        assertThat(iteration.getColumnName(card), is(equalTo("starting")));
    }

    @Test
    public void shouldMoveCard() {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        iteration.moveCard(card, "done");
        assertThat(iteration.getColumnName(card), is(equalTo("done")));
    }

    @Test
    public void shouldMoveCardBackWhenUndoLastTransition() {
        Iteration iteration = new Iteration();
        Card card = new Card("testone", "first card", 1);
        iteration.addCard(card);
        iteration.moveCard(card, "done");
        iteration.undoLastMove();
        assertThat(iteration.getColumnName(card), is(equalTo("starting")));
    }

    // what behaviour is required when transitioning to a column that does not exist?
}
