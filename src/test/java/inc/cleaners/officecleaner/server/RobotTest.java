package inc.cleaners.officecleaner.server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import inc.cleaners.officecleaner.api.Command;
import inc.cleaners.officecleaner.api.Direction;
import inc.cleaners.officecleaner.api.Location;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.testng.Assert.assertEquals;

public class RobotTest {

    private OfficeSpace office;
    private Robot robot;

    @BeforeMethod
    public void setUp() {
        office = new OfficeSpace();
        robot = new Robot(Location.ORIGO, office);
    }

    @Test
    public void emptyCommandListShouldOnlyCleanStartLocation() {
        CleaningReport report = robot.executeCleaning(emptyList());
        assertEquals(report.placesCleaned(), 1);
        assertEquals(report.steps(), 0);
    }

    @Test
    public void simplePath() {
        List<Command> commands = asList(
            new Command(Direction.north, 2),
            new Command(Direction.east, 1)
        );

        CleaningReport report = robot.executeCleaning(commands);

        assertEquals(report.placesCleaned(), 4);
        assertEquals(report.steps(), 3);
    }

    @Test
    public void goingAroundTwice() {
        List<Command> commands = asList(
            new Command(Direction.north, 1),
            new Command(Direction.east, 1),
            new Command(Direction.south, 1),
            new Command(Direction.west, 1),
            new Command(Direction.north, 1),
            new Command(Direction.east, 1),
            new Command(Direction.south, 1),
            new Command(Direction.west, 1)
        );
        CleaningReport report = robot.executeCleaning(commands);

        assertEquals(report.placesCleaned(), 4);
        assertEquals(report.steps(), 8);
        assertEquals(robot.currentLocation(), Location.ORIGO);
    }

    @Test
    public void randomCleaning() {
        List<Command> commands = Stream.generate(() -> new Command(randomDirection(), randomSteps(6)))
                                       .limit(100)
                                       .collect(Collectors.toList());
        robot.executeCleaning(commands);
        printCleaning(office);
    }

    @Test
    public void testSpeed() {
        List<Command> commands = Stream.generate(RobotTest::randomCommand)
                                       .limit(10000)
                                       .collect(Collectors.toList());
        long start = System.nanoTime();
        CleaningReport report = robot.executeCleaning(commands);
        long duration = System.nanoTime() - start;
        System.out.println("---Report---");
        System.out.println("steps:" + report.steps());
        System.out.println("cleanedPlaces:" + report.placesCleaned());
        System.out.println("duration:" + ((double) duration) / 1000000000);
    }

    public static Command randomCommand() {
        return new Command(randomDirection(), randomSteps(20));
    }

    private static int randomSteps(int max) {
        return ThreadLocalRandom.current().nextInt(1, max);
    }

    private static Direction randomDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(0, 4)];
    }

    private static void printCleaning(OfficeSpace office) {
        for (int y = -50; y < 50; y++) {
            for (int x = -50; x < 50; x++) {
                System.out.print(office.getPlaceAt(new Location(x, y)).isCleaned() ? "*" : " ");
            }
            System.out.print("\n");
        }
    }
}