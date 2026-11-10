package config;

import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.aeonbits.owner.ConfigFactory;

@UtilityClass
public class ConfigurationManager {

  @Getter private final Configuration config = ConfigFactory.create(Configuration.class);
  @Getter private final String baseUrl = config.getBaseUrl();
  @Getter private final String apiUrl = config.getApiUrl();
}
