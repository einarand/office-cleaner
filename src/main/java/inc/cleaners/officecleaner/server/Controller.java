package inc.cleaners.officecleaner.server;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import inc.cleaners.officecleaner.api.CleaningReportResponse;
import inc.cleaners.officecleaner.api.CleaningRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class Controller {

    private static Logger logger = Logger.getLogger("Controller");
    private final CleaningReportsRepository store;
    private final ResourceLocations locations;

    public Controller(CleaningReportsRepository store, ResourceLocations locations) {
        this.store = store;
        this.locations = locations;
    }

    @RequestMapping(value = "/ping", method = GET)
    public String ping() {
        return "pong";
    }

    @RequestMapping(value = "/tibber-developer-test/enter-path", method = POST)
    public ResponseEntity<CleaningReportResponse> cleanOffice(@RequestBody CleaningRequest request) {
        logger.info("cleanOffice called with " + request);
        Robot robot = new Robot(request.startLocation(), new OfficeSpace());
        CleaningReport report = robot.executeCleaning(request.commands());
        store.save(report);
        return ResponseEntity.created(locations.fromCurrentRequest(report.getId()))
                             .body(cleaningReportResponse(report));
    }

    @RequestMapping(value = "/tibber-developer-test/enter-path/{id}", method = GET)
    public ResponseEntity<CleaningReportResponse> getCleaningResult(@PathVariable("id") UUID id) {
        logger.info("getCleaningResult called with id:" + id);
        Optional<CleaningReport> report = store.findById(id);
        return report.map(r -> ResponseEntity.ok(cleaningReportResponse(r)))
                     .orElse(ResponseEntity.notFound().build());
    }

    private static CleaningReportResponse cleaningReportResponse(CleaningReport report) {
        return new CleaningReportResponse(report.timestamp(),
                                          report.commands(),
                                          report.placesCleaned(),
                                          report.steps(),
                                          report.durationInSeconds());
    }

}
