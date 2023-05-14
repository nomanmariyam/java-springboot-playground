package de.anshaana.playground.infrastructure.incoming;

import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptResponse;
import de.anshaana.playground.infrastructure.chatgpt.models.EmbeddingResponse;
import reactor.core.publisher.Mono;

public interface AiRepository {
    ChatGptResponse completion(String prompt);

    Mono<EmbeddingResponse> embeddings(String input);
}
