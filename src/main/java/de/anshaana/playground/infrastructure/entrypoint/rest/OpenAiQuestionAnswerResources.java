package de.anshaana.playground.infrastructure.entrypoint.rest;

import de.anshaana.playground.infrastructure.outgoing.OpenAIQuestionAnswerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class OpenAiQuestionAnswerResources {

    private final OpenAIQuestionAnswerService openAIQuestionAnswerService;

    public OpenAiQuestionAnswerResources(OpenAIQuestionAnswerService openAIQuestionAnswerService) {
        this.openAIQuestionAnswerService = openAIQuestionAnswerService;
    }

    @PostMapping("/askMe")
    public String askMe(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        String inputData = request.get("inputData");
        return openAIQuestionAnswerService.getAnswer(question, inputData);
    }
}
