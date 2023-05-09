package de.anshaana.playground.infrastructure.chatgpt.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.StringJoiner;

public class ChatGptUsage {
    @JsonProperty("promptTokens")
    private int promptTokens;

    @JsonProperty("completion_tokens")
    private int completionTokens;

    @JsonProperty("total_tokens")
    private int totalTokens;

    public int getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(int promptTokens) {
        this.promptTokens = promptTokens;
    }

    public int getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(int completionTokens) {
        this.completionTokens = completionTokens;
    }

    public int getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(int totalTokens) {
        this.totalTokens = totalTokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatGptUsage that = (ChatGptUsage) o;

        if (promptTokens != that.promptTokens) return false;
        if (completionTokens != that.completionTokens) return false;
        return totalTokens == that.totalTokens;
    }

    @Override
    public int hashCode() {
        int result = promptTokens;
        result = 31 * result + completionTokens;
        result = 31 * result + totalTokens;
        return result;
    }

    @Override
    public String
    toString() {
        return new StringJoiner(", ", ChatGptUsage.class.getSimpleName() + "[", "]")
            .add("promptTokens=" + promptTokens)
            .add("completionTokens=" + completionTokens)
            .add("totalTokens=" + totalTokens)
            .toString();
    }
}
