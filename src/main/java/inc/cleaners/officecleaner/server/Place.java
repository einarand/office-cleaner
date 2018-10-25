package inc.cleaners.officecleaner.server;


public final class Place {

    private boolean cleaned;

    public Place() {
    }

    public boolean isCleaned() {
        return cleaned;
    }

    public void clean() {
        cleaned = true;
    }
}
