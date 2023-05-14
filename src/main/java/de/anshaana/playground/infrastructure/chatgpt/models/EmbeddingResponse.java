package de.anshaana.playground.infrastructure.chatgpt.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class EmbeddingResponse {

    private List<EmbeddingData> data;

    private String model;

    @JsonProperty("usage")
    private ChatGptUsage chatGptUsage;

    private String errorMessage;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<EmbeddingData> getData() {
        return data;
    }

    public void setData(List<EmbeddingData> data) {
        this.data = data;
    }

    public ChatGptUsage getChatGptUsage() {
        return chatGptUsage;
    }

    public void setChatGptUsage(ChatGptUsage chatGptUsage) {
        this.chatGptUsage = chatGptUsage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmbeddingResponse that = (EmbeddingResponse) o;

        if (!Objects.equals(data, that.data)) return false;
        if (!Objects.equals(model, that.model)) return false;
        if (!Objects.equals(chatGptUsage, that.chatGptUsage)) return false;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        int result = data != null ? data.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (chatGptUsage != null ? chatGptUsage.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EmbeddingResponse.class.getSimpleName() + "[", "]")
            .add("data=" + data)
            .add("model='" + model + "'")
            .add("chatGptUsage=" + chatGptUsage)
            .add("errorMessage='" + errorMessage + "'")
            .toString();
    }
}
