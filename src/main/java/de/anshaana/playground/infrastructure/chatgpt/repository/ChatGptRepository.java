package de.anshaana.playground.infrastructure.chatgpt.repository;

import com.google.gson.JsonObject;
import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptResponse;
import de.anshaana.playground.infrastructure.configuration.ChatGptSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class ChatGptRepository {

    private final Logger log = LoggerFactory.getLogger(ChatGptRepository.class);

    private final WebClient webClient;

    private ChatGptSettings chatGptSettings;

    public ChatGptRepository(WebClient webClient, ChatGptSettings chatGptSettings) {
        this.webClient = webClient;
        this.chatGptSettings = chatGptSettings;
    }

    public Mono<ChatGptResponse> askChatGPT(String prompt) {
        JsonObject requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("prompt", prompt);
        requestBodyJson.addProperty("max_tokens", chatGptSettings.getMaxTokens());
        requestBodyJson.addProperty("model", chatGptSettings.getCompletionModel());
        requestBodyJson.addProperty("n", chatGptSettings.getCompletionCount());
        requestBodyJson.addProperty("temperature", chatGptSettings.getTemperature());

        return webClient
            .post()
            .uri(chatGptSettings.getBaseUrl() + "/completions")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + chatGptSettings.getApiKey())
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(requestBodyJson.toString()))
            .retrieve()
            .bodyToMono(ChatGptResponse.class)
            .onErrorResume(e -> {
                log.error("error while processing the request for question {}", prompt, e);
                String errorMessage = "An error occurred while processing the request: " + e.getMessage();
                ChatGptResponse chatGptResponse = new ChatGptResponse();
                chatGptResponse.setErrorMessage(errorMessage);
                return Mono.just(chatGptResponse);
            });
    }
}