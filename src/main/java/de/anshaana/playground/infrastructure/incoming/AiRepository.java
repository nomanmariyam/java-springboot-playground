package de.anshaana.playground.infrastructure.incoming;

import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptResponse;

public interface AiRepository {
    ChatGptResponse completion(String prompt);
}
