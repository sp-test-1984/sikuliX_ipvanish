package sp.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.sikuli.script.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;

public class Configuration {

    private static final Config BASE_CONFING = ConfigFactory.load();
    private static final Logger LOGGER = LoggerFactory.getLogger(Configuration.class);
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();
    private static final ObjectMapper MAPPER = buildMapper();
    private static final Configuration CONFIGURATION = buildSingleton(BASE_CONFING);
    private static final Screen SCREEN = buildScreen();

    @Valid
    @NotNull
    private String applicationPath;

    @Valid
    @NotNull
    private String userName;

    @Valid
    @NotNull
    private String password;


    public String getUserName() {
        return userName;
    }

    @NotNull
    public String getPassword() {
        return password;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public Screen getScreen() { return SCREEN; }

    public static Configuration getCONFIGURATION() {
        return CONFIGURATION;
    }

    private static ObjectMapper buildMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    private static Configuration buildSingleton(Config config){
        Map<String, Object> unwrapped = config.root().unwrapped();
        Configuration configuration = MAPPER.convertValue(unwrapped, Configuration.class);
        Set<ConstraintViolation<Configuration>> violations = VALIDATOR.validate(configuration);
        if (!violations.isEmpty()){
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<Configuration> violation:
                 violations) {
                message.append(" ");
                message.append(violation.getPropertyPath());
                message.append(" : ");
                message.append(violation.getMessage());
            }
            LOGGER.error("config is not valid");
            throw new IllegalStateException();
        }
        return configuration;
    }

    private static Screen buildScreen() {
        Screen screen = new Screen();
        return screen;
    }
}
