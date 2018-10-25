package inc.cleaners.officecleaner.server;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface CleaningReportsRepository extends CrudRepository<CleaningReport, UUID> {

}
