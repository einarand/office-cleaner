package inc.cleaners.officecleaner.server;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import inc.cleaners.officecleaner.api.CleaningReportResponse;
import inc.cleaners.officecleaner.api.CleaningRequest;
import inc.cleaners.officecleaner.api.Command;
import inc.cleaners.officecleaner.api.Direction;
import inc.cleaners.officecleaner.api.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ControllerTest {

    private CleaningReportsRepository store;
    private Controller controller;
    private List<Command> commands;

    @BeforeMethod
    public void setUp() {
        store = mock(CleaningReportsRepository.class);
        ResourceLocations location = mockResourceLocations();
        controller = new Controller(store, location);
        commands = asList(
            new Command(Direction.north, 1),
            new Command(Direction.east, 1),
            new Command(Direction.south, 1),
            new Command(Direction.west, 1)
        );
    }

    @Test
    public void cleanOffice() {
        //Setup
        CleaningRequest request = new CleaningRequest(Location.ORIGO, commands);

        //Exercise
        ResponseEntity<CleaningReportResponse> response = controller.cleanOffice(request);

        //Verify response
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertTrue(response.getHeaders().containsKey("location"));
        verifyResponseBody(response);

        //Verify DB interactions
        verify(store).save(any(CleaningReport.class));
        verifyNoMoreInteractions(store);
    }

    @Test
    public void getCleaningReport_ok() {
        //Setup
        UUID id = UUID.randomUUID();
        when(store.findById(id)).thenReturn(Optional.of(new CleaningReport(4, 4, 4, 0.001)));

        //Exercise
        ResponseEntity<CleaningReportResponse> response = controller.getCleaningResult(id);

        //Verify response
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        verifyResponseBody(response);

        //Verify DB interactions
        verify(store).findById(id);
        verifyNoMoreInteractions(store);
    }

    @Test
    public void getCleaningReport_notFound() {
        //Setup
        UUID id = UUID.randomUUID();
        when(store.findById(id)).thenReturn(Optional.empty());

        //Exercise
        ResponseEntity<CleaningReportResponse> response = controller.getCleaningResult(id);

        //Verify response
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

        //Verify DB interactions
        verify(store).findById(id);
        verifyNoMoreInteractions(store);
    }

    private static void verifyResponseBody(ResponseEntity<CleaningReportResponse> responseEntity) {
        CleaningReportResponse response = responseEntity.getBody();
        assertNotNull(response);
        assertEquals(response.commands(), 4);
        assertEquals(response.steps(), 4);
        assertEquals(response.placesCleaned(), 4);
        assertNotEquals(response.durationInSeconds(), 0.0);
    }

    private static ResourceLocations mockResourceLocations() {
        ResourceLocations location = mock(ResourceLocations.class);
        when(location.fromCurrentRequest(any(UUID.class))).thenReturn(URI.create("http://path/to/the/resource"));
        return location;
    }

}