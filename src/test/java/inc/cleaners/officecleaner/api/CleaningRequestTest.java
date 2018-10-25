package inc.cleaners.officecleaner.api;

import java.util.List;

import org.testng.annotations.Test;

import static inc.cleaners.officecleaner.tools.Json.mapper;
import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class CleaningRequestTest {


    @Test
    public void serializingDeserializing() throws Exception {
        List<Command> commands = asList(new Command(Direction.north, 2), new Command(Direction.east, 1));
        CleaningRequest before = new CleaningRequest(Location.ORIGO, commands);
        String json = mapper().writeValueAsString(before);
        System.out.println(json);

        CleaningRequest after = mapper().readValue(json, CleaningRequest.class);
        assertEquals(before.startLocation(), after.startLocation());
        assertEquals(after.commands().size(), 2);
    }

}