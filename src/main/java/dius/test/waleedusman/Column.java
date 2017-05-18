package dius.test.waleedusman;

import static java.lang.String.format;

public class Column {
    private final String name;
    private final Integer workInProgressLimit;
    private int totalPoints = 0;

    public Column(String name) {
        this.name = name;
        this.workInProgressLimit = null;
    }

    public Column(String name, int workInProgressLimit) {
        this.name = name;
        this.workInProgressLimit = workInProgressLimit;
    }

    public String getName() {
        return name;
    }

    public void addPoints(int points) throws WorkExceededException {
        if ((workInProgressLimit != null)
                && (totalPoints + points > workInProgressLimit)) {
            throw new WorkExceededException(format("Work for column [%s] cannot exceed [%d]", name, workInProgressLimit));
        }
        totalPoints += points;
    }
}
