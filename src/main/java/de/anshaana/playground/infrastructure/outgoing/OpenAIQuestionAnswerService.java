package de.anshaana.playground.infrastructure.outgoing;

public interface OpenAIQuestionAnswerService {

    String getAnswer(String question, String inputData);
}
