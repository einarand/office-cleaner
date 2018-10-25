package inc.cleaners.officecleaner.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Command {

    private Direction direction;
    private int steps;

    @JsonCreator
    public Command(@JsonProperty("direction") Direction direction,
                   @JsonProperty("steps") int steps) {
        this.direction = direction;
        this.steps = steps;
    }

    @JsonProperty("direction")
    public Direction direction() {
        return direction;
    }

    @JsonProperty("steps")
    public int steps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return steps == command.steps &&
            direction == command.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, steps);
    }
}
