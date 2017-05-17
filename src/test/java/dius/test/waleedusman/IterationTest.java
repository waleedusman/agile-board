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
}
