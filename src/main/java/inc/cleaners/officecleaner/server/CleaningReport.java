package inc.cleaners.officecleaner.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "executions")
public final class CleaningReport {

    @Id
    private UUID id;

    @Column(name = "timestamp")
    private Instant timestamp;

    @Column(name = "commands")
    private int commands;

    @Column(name = "result")
    private int placesCleaned;

    @Column(name = "steps")
    private int steps;

    @Column(name = "duration", precision = 6)
    private double duration;

    private CleaningReport() {
        //Required by Hibernate
    }

    public CleaningReport(int commands, int placesCleaned, int steps, double duration) {
        this(UUID.randomUUID(), Instant.now(), commands, placesCleaned, steps, duration);
    }

    public CleaningReport(UUID id, Instant timestamp, int commands, int placesCleaned, int steps, double duration) {
        this.id = id;
        this.timestamp = timestamp;
        this.commands = commands;
        this.placesCleaned = placesCleaned;
        this.steps = steps;
        this.duration = duration;
    }

    public UUID getId() {
        return id;
    }

    public int placesCleaned() {
        return placesCleaned;
    }

    public int steps() {
        return steps;
    }

    public Instant timestamp() {
        return timestamp;
    }

    public double durationInSeconds() {
        return duration;
    }

    public int commands() {
        return commands;
    }

}
