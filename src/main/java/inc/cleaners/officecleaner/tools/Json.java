package inc.cleaners.officecleaner.tools;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class Json {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES);
        mapper.enable(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
        mapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
    }

    public static ObjectMapper mapper() {
        return mapper;
    }

}
