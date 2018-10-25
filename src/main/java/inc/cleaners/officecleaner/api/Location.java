package inc.cleaners.officecleaner.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Location {

    public static Location ORIGO = new Location(0, 0);

    @JsonProperty("x")
    private final int x;
    @JsonProperty("y")
    private final int y;

    @JsonCreator
    public Location(@JsonProperty("x") int x,
                    @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public Location advance(Direction direction) {
        return new Location(x + direction.x(), y + direction.y());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
