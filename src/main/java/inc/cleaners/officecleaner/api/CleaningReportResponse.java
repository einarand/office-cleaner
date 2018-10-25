package inc.cleaners.officecleaner.api;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class CleaningReportResponse {

    private final Instant timestamp;
    private final int commands;
    private final int placesCleaned;
    private final int steps;
    private final double durationInSeconds;

    public CleaningReportResponse(@JsonProperty("timestamp") Instant timestamp,
                                  @JsonProperty("commandsIssued")int commands,
                                  @JsonProperty("placesCleaned")int placesCleaned,
                                  @JsonProperty("steps")int steps,
                                  @JsonProperty("durationInSeconds") double durationInSeconds) {
        this.timestamp = timestamp;
        this.commands = commands;
        this.placesCleaned = placesCleaned;
        this.steps = steps;
        this.durationInSeconds = durationInSeconds;
    }

    @JsonProperty("timestamp")
    public Instant timestamp() {
        return timestamp;
    }

    @JsonProperty("commandsIssued")
    public int commands() {
        return commands;
    }

    @JsonProperty("placesCleaned")
    public int placesCleaned() {
        return placesCleaned;
    }

    @JsonProperty("durationInSeconds")
    public double durationInSeconds() {
        return durationInSeconds;
    }

    @JsonProperty("steps")
    public int steps() {
        return steps;
    }

}
