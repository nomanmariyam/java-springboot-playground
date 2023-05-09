package de.anshaana.playground.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.StringJoiner;

@Configuration
@ConfigurationProperties(prefix = "webclient-config")
public class WebClientSettings {
  private int connectionTimeout;

  private boolean wiretapEnabled;

  private int bufferSizeInByte;

  public int getConnectionTimeout() {
    return connectionTimeout;
  }

  public void setConnectionTimeout(int connectionTimeout) {
    this.connectionTimeout = connectionTimeout;
  }

  public boolean isWiretapEnabled() {
    return wiretapEnabled;
  }

  public void setWiretapEnabled(boolean wiretapEnabled) {
    this.wiretapEnabled = wiretapEnabled;
  }

  public int getBufferSizeInByte() {
    return bufferSizeInByte;
  }

  public void setBufferSizeInByte(int bufferSizeInByte) {
    this.bufferSizeInByte = bufferSizeInByte;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", WebClientSettings.class.getSimpleName() + "[", "]")
            .add("connectionTimeout=" + connectionTimeout)
            .add("wiretapEnabled=" + wiretapEnabled)
            .add("bufferSizeInByte=" + bufferSizeInByte)
            .toString();
  }
}
