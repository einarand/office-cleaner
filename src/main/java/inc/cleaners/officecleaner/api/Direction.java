package inc.cleaners.officecleaner.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Direction {
    @JsonProperty("north") north(0, 1),
    @JsonProperty("south") south(0, -1),
    @JsonProperty("west") west(-1, 0),
    @JsonProperty("east") east(1, 0);

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
