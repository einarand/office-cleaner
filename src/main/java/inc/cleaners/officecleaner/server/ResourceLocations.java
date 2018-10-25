package inc.cleaners.officecleaner.server;

import java.net.URI;
import java.util.UUID;

public interface ResourceLocations {

    URI fromCurrentRequest(UUID id);

}
