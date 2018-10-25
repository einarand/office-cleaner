package inc.cleaners.officecleaner.api;

import java.time.Instant;

import org.testng.annotations.Test;

import static inc.cleaners.officecleaner.tools.Json.mapper;
import static org.testng.Assert.assertEquals;

public class CleaningReportResponseTest {

    @Test
    public void serializingDeserializing() throws Exception {
        CleaningReportResponse before = new CleaningReportResponse(Instant.now(), 1, 2, 4, 0.01);
        String json = mapper().writeValueAsString(before);

        CleaningReportResponse after = mapper().readValue(json, CleaningReportResponse.class);
        assertEquals(after.timestamp(), before.timestamp());
        assertEquals(after.commands(), before.commands());
        assertEquals(after.placesCleaned(), before.placesCleaned());
        assertEquals(after.steps(), before.steps());
        assertEquals(after.durationInSeconds(), before.durationInSeconds());
    }

}