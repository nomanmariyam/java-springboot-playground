package de.anshaana.playground.infrastructure.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.StringJoiner;

@Configuration
@ConfigurationProperties(prefix = "chatgpt-config")
public class ChatGptSettings {
    private String apiKey;
    private String baseUrl;
    private String completionModel;
    private int maxTokens;
    private double temperature;
    private int completionCount;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getCompletionModel() {
        return completionModel;
    }

    public void setCompletionModel(String completionModel) {
        this.completionModel = completionModel;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getCompletionCount() {
        return completionCount;
    }

    public void setCompletionCount(int completionCount) {
        this.completionCount = completionCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatGptSettings that = (ChatGptSettings) o;

        if (maxTokens != that.maxTokens) return false;
        if (Double.compare(that.temperature, temperature) != 0) return false;
        if (!Objects.equals(apiKey, that.apiKey)) return false;
        if (!Objects.equals(baseUrl, that.baseUrl)) return false;
        return Objects.equals(completionModel, that.completionModel);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = apiKey != null ? apiKey.hashCode() : 0;
        result = 31 * result + (baseUrl != null ? baseUrl.hashCode() : 0);
        result = 31 * result + (completionModel != null ? completionModel.hashCode() : 0);
        result = 31 * result + maxTokens;
        temp = Double.doubleToLongBits(temperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatGptSettings.class.getSimpleName() + "[", "]")
            .add("apiKey='" + apiKey + "'")
            .add("baseUrl='" + baseUrl + "'")
            .add("completionModel='" + completionModel + "'")
            .add("maxTokens=" + maxTokens)
            .add("temperature=" + temperature)
            .toString();
    }

}

