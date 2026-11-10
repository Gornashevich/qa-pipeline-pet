package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:base.properties"})
public interface Configuration extends Config {

  @Key("base_url")
  String getBaseUrl();

  @Key("api_url")
  String getApiUrl();
}
