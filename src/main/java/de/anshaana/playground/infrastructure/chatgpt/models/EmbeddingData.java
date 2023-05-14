package de.anshaana.playground.infrastructure.chatgpt.models;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class EmbeddingData {
    private List<Float> embedding;
    private int index;

    public List<Float> getEmbedding() {
        return embedding;
    }

    public void setEmbedding(List<Float> embedding) {
        this.embedding = embedding;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmbeddingData that = (EmbeddingData) o;

        if (index != that.index) return false;
        return Objects.equals(embedding, that.embedding);
    }

    @Override
    public int hashCode() {
        int result = embedding != null ? embedding.hashCode() : 0;
        result = 31 * result + index;
        return result;
    }

    @Override
    public String
    toString() {
        return new StringJoiner(", ", EmbeddingData.class.getSimpleName() + "[", "]")
            .add("embedding=" + embedding)
            .add("index=" + index)
            .toString();
    }
}
