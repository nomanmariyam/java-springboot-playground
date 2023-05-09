package de.anshaana.playground.infrastructure.chatgpt.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ChatGptResponse {

    private String model;

    private List<ChatGptChoices> choices;

    @JsonProperty("usage")
    private ChatGptUsage chatGptUsage;

    private String errorMessage;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<ChatGptChoices> getChoices() {
        return choices;
    }

    public void setChoices(List<ChatGptChoices> choices) {
        this.choices = choices;
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

        ChatGptResponse that = (ChatGptResponse) o;

        if (!Objects.equals(model, that.model)) return false;
        if (!Objects.equals(choices, that.choices)) return false;
        if (!Objects.equals(chatGptUsage, that.chatGptUsage)) return false;
        return Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + (choices != null ? choices.hashCode() : 0);
        result = 31 * result + (chatGptUsage != null ? chatGptUsage.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatGptResponse.class.getSimpleName() + "[", "]")
            .add("model='" + model + "'")
            .add("choices=" + choices)
            .add("chatGptUsage=" + chatGptUsage)
            .add("errorMessage='" + errorMessage + "'")
            .toString();
    }
}
