package de.anshaana.playground.infrastructure.openai.repository;

import com.google.gson.JsonObject;
import de.anshaana.playground.infrastructure.chatgpt.configuration.ChatGptSettings;
import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptResponse;
import de.anshaana.playground.infrastructure.incoming.AiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class OpenAiRepository implements AiRepository {

    private final Logger log = LoggerFactory.getLogger(OpenAiRepository.class);

    private final WebClient webClient;

    private ChatGptSettings chatGptSettings;

    public OpenAiRepository(WebClient webClient, ChatGptSettings chatGptSettings) {
        this.webClient = webClient;
        this.chatGptSettings = chatGptSettings;
    }

    public ChatGptResponse completion(String prompt) {
        JsonObject requestBodyJson = new JsonObject();
        requestBodyJson.addProperty("prompt", prompt);
        requestBodyJson.addProperty("max_tokens", chatGptSettings.getMaxTokens());
        requestBodyJson.addProperty("model", chatGptSettings.getCompletionModel());
        requestBodyJson.addProperty("n", chatGptSettings.getCompletionCount());
        requestBodyJson.addProperty("temperature", chatGptSettings.getTemperature());

        Mono<ChatGptResponse> chatGptResponseMono = webClient
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
        return chatGptResponseMono.block();
    }
}
