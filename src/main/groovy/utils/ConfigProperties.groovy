package utils

import org.apache.commons.configuration2.Configuration
import org.apache.commons.configuration2.YAMLConfiguration

class ConfigProperties {
  private static final String DEFAULT_APP_BASE_URL = 'http://localhost:8080/api/v1'
  private static final String DEFAULT_TIME_ZONE = "UTC"

  private static final String CONFIG_DIR = "./src/main/resources/config.yml"

  private static final ConfigProperties INSTANCE = new ConfigProperties()

  private Configuration configuration

	ConfigProperties() {
    configuration = load()
  }

  static ConfigProperties getInstance() {
    return INSTANCE;
  }

  def getBaseUrl() {
    configuration.getString('base-url', DEFAULT_APP_BASE_URL)
  }

  def getTimeZone() {
    configuration.getString('time-zone', DEFAULT_TIME_ZONE)
  }

  private static YAMLConfiguration load() {
    final File yamlFile = new File(CONFIG_DIR)

    def configuration = new YAMLConfiguration()
    configuration.read(yamlFile.newInputStream())
    return configuration
  }
}
