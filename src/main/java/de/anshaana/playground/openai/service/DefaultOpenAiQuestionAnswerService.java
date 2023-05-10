package de.anshaana.playground.openai.service;

import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptChoices;
import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptResponse;
import de.anshaana.playground.infrastructure.incoming.AiRepository;
import de.anshaana.playground.infrastructure.outgoing.OpenAIQuestionAnswerService;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultOpenAiQuestionAnswerService implements OpenAIQuestionAnswerService {

    private AiRepository openAiRepository;

    public DefaultOpenAiQuestionAnswerService(AiRepository openAiRepository) {
        this.openAiRepository = openAiRepository;
    }

    @Override
    public String getAnswer(String question, String inputData) {
        if(StringUtils.isBlank(question)) {
            return "Please ask question , do not joke around";
        }

        if(StringUtils.isBlank(inputData)) {
            return "Please provide input data, other i will say anything";
        }

        String prompt =
                String.format("What is the answer to the question '%s' based on the input data: '%s'", question, inputData);

        ChatGptResponse chatGptResponse = openAiRepository.completion(prompt);
        return adaptResponse(chatGptResponse);
    }

    private String adaptResponse(ChatGptResponse chatGptResponse) {
        if(chatGptResponse != null) {
            List<ChatGptChoices> choices = ListUtils.emptyIfNull(chatGptResponse.getChoices());
            if(!choices.isEmpty()) {
                return choices.get(0).getText();
            }
        }
        return "your question was not a good question";
    }
}

