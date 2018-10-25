package inc.cleaners.officecleaner.server;

import java.util.List;

import inc.cleaners.officecleaner.api.Command;
import inc.cleaners.officecleaner.api.Direction;
import inc.cleaners.officecleaner.api.Location;

//Note: This class is mutable
public class Robot {

    private final OfficeSpace officeSpace;
    private int placesCleaned = 0;
    private int steps = 0;
    private long startedAt;

    private Location currentLocation;

    public Robot(Location start, OfficeSpace officeSpace) {
        this.currentLocation = start;
        this.officeSpace = officeSpace;
    }

    public CleaningReport executeCleaning(List<Command> commands) {
        startedAt = System.nanoTime();
        cleanCurrentLocationIfNotAlreadyCleaned();
        commands.forEach(c -> moveAndClean(c.direction(), c.steps()));
        double duration = nanosInSeconds(now() - startedAt); //TODO handle wrap around
        return new CleaningReport(commands.size(), placesCleaned, steps, duration);
    }

    private void moveAndClean(Direction direction, int steps) {
        for (int i = 0; i < steps; i++) {
            advanceOneStep(direction);
            cleanCurrentLocationIfNotAlreadyCleaned();
        }
    }

    private void advanceOneStep(Direction direction) {
        currentLocation = currentLocation.advance(direction);
        steps++;
    }

    private void cleanCurrentLocationIfNotAlreadyCleaned() {
        Place place = officeSpace.getPlaceAt(currentLocation);
        if (!place.isCleaned()) {
            place.clean();
            placesCleaned++;
        }
    }

    private static long now() {
        return System.nanoTime();
    }

    private static double nanosInSeconds(long nanos) {
        return (double) nanos / 1000000000;
    }

    //Used for tests
    Location currentLocation() {
        return currentLocation;
    }



}
