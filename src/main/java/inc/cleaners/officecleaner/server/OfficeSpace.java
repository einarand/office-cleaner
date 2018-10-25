package inc.cleaners.officecleaner.server;

import java.util.HashMap;
import java.util.Map;

import inc.cleaners.officecleaner.api.Location;

public final class OfficeSpace {

    private Map<Location, Place> places = new HashMap<>();

    public Place getPlaceAt(Location location) {
        return places.computeIfAbsent(location, a -> new Place()); //Lazy creation
    }
}
