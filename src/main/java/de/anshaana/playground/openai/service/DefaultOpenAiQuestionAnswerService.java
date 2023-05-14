package de.anshaana.playground.openai.service;

import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptChoices;
import de.anshaana.playground.infrastructure.chatgpt.models.ChatGptResponse;
import de.anshaana.playground.infrastructure.chatgpt.models.EmbeddingData;
import de.anshaana.playground.infrastructure.chatgpt.models.EmbeddingResponse;
import de.anshaana.playground.infrastructure.incoming.AiRepository;
import de.anshaana.playground.infrastructure.outgoing.OpenAIQuestionAnswerService;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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

        List<String> inputDataSets = devideInputDataIntoChunks(inputData);

        List<List<Float>> inputDataEmbeddings = getDataSetEmbeddings(inputDataSets);

        List<Float> questionEmbeddings = getQuestionEmbeddings(question);

        double[] cosineSimilarities = getCosineSimilarities(inputDataSets, inputDataEmbeddings, questionEmbeddings);

        int mostSimilarIndex = getMostRelevantIndex(cosineSimilarities);

        String mostRelevantSection = inputDataSets.get(mostSimilarIndex);

        String prompt =
                String.format("What is the answer to the question '%s' based on the text section: '%s'", question, mostRelevantSection);

        ChatGptResponse chatGptResponse = openAiRepository.completion(prompt);

        return adaptResponse(chatGptResponse);
    }

    private static int getMostRelevantIndex(double[] cosineSimilarities) {
        int mostSimilarIndex = 0;
        double highestSimilarity = Double.MIN_VALUE;
        for (int i = 0; i < cosineSimilarities.length; i++) {
            if (cosineSimilarities[i] > highestSimilarity) {
                highestSimilarity = cosineSimilarities[i];
                mostSimilarIndex = i;
            }
        }
        return mostSimilarIndex;
    }


    private static double[] getCosineSimilarities(List<String> dataSet, List<List<Float>> productInfoEmbeddings, List<Float> questionEmbeddings) {
        double[] cosineSimilarities = new double[dataSet.size()];
        for (int i = 0; i < dataSet.size(); i++) {
            List<Float> datasetEmbedding = productInfoEmbeddings.get(i);
            double dotProduct = 0;
            double normDataset = 0;
            double normQuery = 0;
            for (int j = 0; j < datasetEmbedding.size(); j++) {
                Float dataSetEmbeddingAtIndex = datasetEmbedding.get(j);
                Float questionEmbeddingAtIndex = questionEmbeddings.get(j);
                dotProduct += dataSetEmbeddingAtIndex * questionEmbeddingAtIndex;
                normDataset += Math.pow(dataSetEmbeddingAtIndex, 2);
                normQuery += Math.pow(questionEmbeddingAtIndex, 2);
            }
            normDataset = Math.sqrt(normDataset);
            normQuery = Math.sqrt(normQuery);
            cosineSimilarities[i] = dotProduct / (normDataset * normQuery);
        }
        return cosineSimilarities;
    }

    private List<List<Float>> getDataSetEmbeddings(List<String> dataSet) {
            List<List<Float>> inputDataEmebddings = new ArrayList<>();
            for (String chunk : dataSet) {
                Mono<EmbeddingResponse> embeddingsResponseMono = openAiRepository.embeddings(chunk);
                EmbeddingResponse inputDataEmbeddingsResponse = embeddingsResponseMono.block();
                if(inputDataEmbeddingsResponse != null) {
                    List<EmbeddingData> inputDataEmbeddingList = ListUtils.emptyIfNull(inputDataEmbeddingsResponse.getData());
                    inputDataEmbeddingList.stream().forEach(embeddingData -> {
                        inputDataEmebddings.add(embeddingData.getEmbedding());
                    });
                }
            }
            return inputDataEmebddings;
    }


    private List<Float> getQuestionEmbeddings(String adaptedQuestion) {
        List<Float> questionEmbeddings = new ArrayList<>();
        Mono<EmbeddingResponse> questionEmbeddingsResponse = openAiRepository.embeddings(adaptedQuestion);
        EmbeddingResponse questionEmbeddingResponse = questionEmbeddingsResponse.block();
        List<EmbeddingData> questionProductInfoEmbeddingList = ListUtils.emptyIfNull(questionEmbeddingResponse.getData());
        questionProductInfoEmbeddingList.stream().forEach(questionEmbeddingDataValue -> {
            questionEmbeddings.addAll(questionEmbeddingDataValue.getEmbedding());
        });
        return questionEmbeddings;
    }


    public static final int TOKEN_CHUNK_SIZE = 2000;
    private static List<String> devideInputDataIntoChunks(String productInfo) {
        List<String> dataSet = new ArrayList<>();
        for (int i = 0; i < productInfo.length(); i += TOKEN_CHUNK_SIZE) {
            dataSet.add(productInfo.substring(i, Math.min(i + TOKEN_CHUNK_SIZE, productInfo.length())));
        }
        return dataSet;
    }

    private String adaptResponse(ChatGptResponse chatGptResponse) {
        if(chatGptResponse != null) {
            List<ChatGptChoices> choices = ListUtils.emptyIfNull(chatGptResponse.getChoices());
            if(!choices.isEmpty()) {
                return choices.get(0).getText();
            }
        }
        return "NotFound";
    }
}

