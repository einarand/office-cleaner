package inc.cleaners.officecleaner.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class CleaningRequest {

    private final Location start;
    private final List<Command> commands;

    @JsonCreator
    public CleaningRequest(@JsonProperty("start") Location start,
                           @JsonProperty("commands") List<Command> commands) {
        this.start = start;
        this.commands = commands;
    }

    @JsonProperty("start")
    public Location startLocation() {
        return start;
    }

    @JsonProperty("commands")
    public List<Command> commands() {
        return commands;
    }

    @Override
    public String toString() {
        return "{start=" + start + ",commands=" + commands.size() + "}";
    }
}
