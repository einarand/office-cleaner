package inc.cleaners.officecleaner.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import inc.cleaners.officecleaner.tools.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class Config {

    private final CleaningReportsRepository repository;

    @Autowired
    public Config(CleaningReportsRepository repository) {
        this.repository = repository;
    }

    @Bean
    public Controller controller() {
        return new Controller(cleaningReportsRepo(), resourceLocation());
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ResourceLocations resourceLocation() {
        return id -> ServletUriComponentsBuilder.fromCurrentRequest()
                                                .path("/{id}")
                                                .buildAndExpand(id).toUri();
    }

    @Bean
    public CleaningReportsRepository cleaningReportsRepo() {
        return repository;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return Json.mapper();
    }

}

