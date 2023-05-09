package de.anshaana.playground.infrastructure.chatgpt.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.StringJoiner;

public class ChatGptChoices {
    private String text;

    private int index;

    private Object logprobs;

    @JsonProperty("finish_reason")
    private String finishReason;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Object getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(Object logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinishReason() {
        return finishReason;
    }

    public void setFinishReason(String finishReason) {
        this.finishReason = finishReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatGptChoices that = (ChatGptChoices) o;

        if (index != that.index) return false;
        if (!Objects.equals(text, that.text)) return false;
        if (!Objects.equals(logprobs, that.logprobs)) return false;
        return Objects.equals(finishReason, that.finishReason);
    }

    @Override
    public int hashCode() {
        int result = text != null ? text.hashCode() : 0;
        result = 31 * result + index;
        result = 31 * result + (logprobs != null ? logprobs.hashCode() : 0);
        result = 31 * result + (finishReason != null ? finishReason.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ChatGptChoices.class.getSimpleName() + "[", "]")
            .add("text='" + text + "'")
            .add("index=" + index)
            .add("logprobs=" + logprobs)
            .add("finishReason='" + finishReason + "'")
            .toString();
    }
}
